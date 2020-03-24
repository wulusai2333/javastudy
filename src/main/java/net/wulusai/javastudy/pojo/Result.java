package net.wulusai.javastudy.pojo;

import java.io.Serializable;

/**
 * @author: wulusai
 * @PackageName: net.wulusai.pojo
 * @ClassName: Result
 * @Description: 返回给前端的封装类
 * @date: 2020/3/17 14:30
 */
public class Result implements Serializable {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean flag) {
        this.flag = flag;
    }

    public Result(boolean flag, Integer code) {
        this.flag = flag;
        this.code = code;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
