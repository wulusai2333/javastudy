package net.wulusai.javastudy.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author huyoufu
 * @TIME 2019/5/14 19:41
 * @description
 */
public class AlipayUtil {
    //page client
    private static AlipayClient ALIPAY_CLIENT_PAGE = null;
    //query client
    private static AlipayClient ALIPAY_CLIENT_QUERY = null;
    //app id
    private static String appid = "2016092900626834";
    //服务器地址  生产环境跟线上环境不一样
    private static String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    //应用私钥
    private static String privateKey = null;
    //应用公钥
    private static String publicKey = null;
    //阿里公钥
    private static String aliPublicKey = null;
    //编码方式 仅支持json,所以选填
    private static String format = "json";
    //字符集
    private static String charset = "utf-8";
    //加密方式
    private static String signType = "RSA2";
    //浏览器回调地址
    private static String returnUrl = "http://api.wulusai.net/order?md=callback";
    //异步回调地址
    private static String notifyUrl = "http://api.wulusai.net/callback?md=notify";

    static {
        //类初始化时调用以下参数
        ResourceBundle alipay_config = ResourceBundle.getBundle("alipay_config");
        appid = alipay_config.getString("APPID");
        serverUrl = alipay_config.getString("serverUrl");
        privateKey = alipay_config.getString("privateKey");
        publicKey = alipay_config.getString("publicKey");
        format = alipay_config.getString("format");
        charset = alipay_config.getString("charset");
        signType = alipay_config.getString("signType");
        returnUrl = alipay_config.getString("returnUrl");
        notifyUrl = alipay_config.getString("notifyUrl");
        aliPublicKey = alipay_config.getString("aliPublicKey");
        //根据公共参数生成支付client
        ALIPAY_CLIENT_PAGE = new DefaultAlipayClient(serverUrl, appid, privateKey, format, charset, publicKey, signType);
        ALIPAY_CLIENT_QUERY = new DefaultAlipayClient(serverUrl, appid, privateKey, format, charset, aliPublicKey, signType);
    }

    /**
     * 生成支付表单
     * @param orderId 订单id
     * @param orderSubject 订单标题
     * @param price 订单价格
     * @return
     * @throws AlipayApiException
     * @throws JsonProcessingException
     */
    public static String generateAlipayTradePagePayRequestForm(String orderId, String orderSubject, double price) throws AlipayApiException, JsonProcessingException {
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        //创建一个交易信息对象
        BizContent bizContent = new BizContent(orderId, price, orderSubject);
        //序列化
        String s = new ObjectMapper().writeValueAsString(bizContent);
        alipayRequest.setBizContent(s);
        //调用SDK生成表单
        return ALIPAY_CLIENT_PAGE.pageExecute(alipayRequest).getBody();
    }

    /**
     * 查询支付结果
     * @param orderId 订单id
     * @return
     * @throws AlipayApiException
     */
    public static boolean generateAlipayTradeQueryRequest(String orderId) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + orderId + "\"" +
                "  }");
        AlipayTradeQueryResponse response = ALIPAY_CLIENT_QUERY.execute(request);
        if (response.isSuccess()) {
            String tradeStatus = response.getTradeStatus();
            if (TradeStatus.TRADE_SUCCESS.name().equals(tradeStatus)) {
                return true;
            }
            System.out.println(tradeStatus);
        }

        return false;
    }

    /**
     * 检查返回参数
     * @param requestParams
     * @return
     */
    public static boolean check(Map<String, String> requestParams) {
        try {
            return AlipaySignature.rsaCheckV1(requestParams, aliPublicKey, charset, signType);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean check_(Map<String, String[]> requestParams) {
        try {
            return AlipaySignature.rsaCheckV2(convert(requestParams), aliPublicKey, charset, signType);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 支付表单测试
     * @param args
     * @throws AlipayApiException
     * @throws JsonProcessingException
     */
    public static void main(String[] args) throws AlipayApiException, JsonProcessingException {
        String s = generateAlipayTradePagePayRequestForm("21A69E814FE44CCD8A4C8457B70BD80E", "123123", 1000);
        System.out.println(s);
    }

    /**
     * 这个方法暂时无用
     * 支付成功后支付宝会调用returnUrl返回结果参数
     * convert方法将 HttpServletRequest的结果集Map<String, String[]>转换为Map<String, String>
     * @param requestParams
     * @return
     */
    private static Map<String, String> convert(Map<String, String[]> requestParams) {
        Map<String, String> result = new HashMap<>();
        if (requestParams != null && requestParams.size() > 0) {
            Set<Map.Entry<String, String[]>> entrySet = requestParams.entrySet();
            for (Map.Entry<String, String[]> entry : entrySet) {

                    result.put(entry.getKey(), entry.getValue()[0]);


            }
        }
        return result;
    }

    /**
     * 枚举 订单结果
     * 枚举类每一个都是类本身对象
     */
    public static enum TradeStatus {
        WAIT_BUYER_PAY, TRADE_CLOSED, TRADE_SUCCESS, TRADE_FINISHED
    }

    public static class BizContent {
        //商户交易订单号
        private String out_trade_no;
        //销售产品码，与支付宝签约的产品码名称。
        //注：目前仅支持FAST_INSTANT_TRADE_PAY
        private String product_code = "FAST_INSTANT_TRADE_PAY";
        //订单的总金额
        private double total_amount;
        //订单标题 必选
        private String subject;
        //	订单描述 可选
        private String body;

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getProduct_code() {
            return product_code;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        private BizContent(String out_trade_no, double total_amount, String subject) {
            this.out_trade_no = out_trade_no;
            this.total_amount = total_amount;
            this.subject = subject;
        }

    }


}
