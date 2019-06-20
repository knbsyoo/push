package com.dqx.jq.service.push.exception;

/**
 * Created By JianBin.Liu on 2019/5/8
 * Description: 业务错误码枚举常量
 * 根据约定，微服务的错误码范围为：103600-103699
 */
public enum BusinessExceptionCode {
    PUSH_EXCEPTION(103600, "友盟推送异常：{0}"),
    BROADCAST_MAXIMUM_TIMES(103601, "友盟当日广播次数上限10次，当前剩余0次，当日无法继续广播，24点时候重置次数。"),

    ;

    private int code;
    private String msg;

    BusinessExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "code:" + this.code + ", msg:" + msg;
    }
}
