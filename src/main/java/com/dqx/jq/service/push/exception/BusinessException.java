package com.dqx.jq.service.push.exception;

import com.dqx.jq.common.exception.BaseException;

/**
 * Created By JianBin.Liu on 2019/5/8
 * Description: 业务异常类
 */
public class BusinessException extends BaseException {


    public BusinessException(String s, Integer code) {
        super(s, code);
    }

    public BusinessException(BusinessExceptionCode resCode) {
        super(resCode.getMsg(), resCode.getCode());
    }

    public BusinessException(BusinessExceptionCode resCode, String msg) {
        super(msg, resCode.getCode());
    }

    public BusinessException(BaseException e) {
        super(e.getMessage(), e.getCode());
    }

}
