package net.wulusai.javastudy.controller;

import com.alipay.api.AlipayApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.wulusai.javastudy.utils.AlipayUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.controller
 * @ClassName: AlipayController
 * @Description: 支付宝支付
 *  {@code @RequestParam(required = false) Map map } get方法接收map ; required=false 表示可以不传值
 *  {@code @RequestBody Map param} post方法接收map
 * @date: 2020/3/18 15:39
 */
@RestController
@CrossOrigin
@RequestMapping("/pay")
public class AlipayController {
    /**
     * 支付完成后返回结果页面
     * 用户付款已经完成,此回调是商家给支付宝的支付完成后跳转的url
     * AlipayUtil.check(map)验证这个信息是不是支付宝发来的
     * @param map
     * @return
     */
    @GetMapping("/callback")
    protected String callBack(@RequestParam(required = false) Map map){
        System.out.println("get"+map);
        boolean check = AlipayUtil.check(map);
        if (check){

        return "success";
        }
        return "failed";
    }

    /**
     * 对于 PC 网站支付的交易，在用户支付完成之后，支付宝会根据 API 中商户传入的 notify_url，通过 POST 请求的形式将支付结果作为参数通知到商户系统。
     * @return
     */
    @PostMapping("/notify")
    protected String notify(@RequestParam(required = false) Map map){

        return "success";
    }

    /**
     * 请求跳转支付页面
     * 用户在此就能完成付款
     * @param money
     * @return
     */
    @GetMapping("/topay/{money}")
    protected String topay(@PathVariable Double money){

        String uuid = UUID.randomUUID().toString();
        String subject ="648氵充值";
        String payRequestForm =null;
        try {
             payRequestForm = AlipayUtil.generateAlipayTradePagePayRequestForm(uuid, subject, money);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payRequestForm;
    }

    /**
     * 支付结果查询
     * 根据商家的订单号查询支付结果
     * @param orderId
     * @return
     */
    @GetMapping("/query/{orderId}")
    protected String query(@PathVariable String orderId){
        boolean b = false;
        try {
            b = AlipayUtil.generateAlipayTradeQueryRequest(orderId);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (b){
            return "query success";
        }
        return "query failed,tis order is not find";
    }
}
