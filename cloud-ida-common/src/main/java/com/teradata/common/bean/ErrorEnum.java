package com.teradata.common.bean;

/**
 *
 * @auther xuyaohui
 * @date 2018/9/18
 */
public enum ErrorEnum {

    E_400(400, "请求处理异常，请稍后再试"),
    ERROR_401(401,"账号权限不足"),
    ERROR_500(500,"系统内部错误"),
    ERROR_10000(10000,"登录Token过期"),
    ERROR_10001(10001,"未登录"),
    ERROR_10002(10002,"账号在其他地方登陆");


    private Integer errorCode;
    private String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
