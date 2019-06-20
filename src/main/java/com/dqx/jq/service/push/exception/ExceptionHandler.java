package com.dqx.jq.service.push.exception;

import com.dqx.jq.common.exception.BaseException;
import com.dqx.jq.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Created By JianBin.Liu on 2019/5/8
 * Description:全局统一异常处理器
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public BaseResponse handler(Exception e) {

        if (e instanceof BusinessException) {//业务异常
            return BaseResponse.error(((BusinessException) e).getCode(), e.getMessage());
        }

        if (e instanceof MethodArgumentNotValidException) {//数据检验异常
            StringBuilder msg = new StringBuilder();

            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            if (bindingResult.hasErrors()) {
                List<ObjectError> errors = bindingResult.getAllErrors();
                for (ObjectError error : errors) {
                    FieldError fieldError = (FieldError) error;
                    msg.append(fieldError.getField() + "：" + fieldError.getDefaultMessage() + "； ");
                }
            }
            BaseException baseException = BaseException.VALIDATE(msg.toString());
            return BaseResponse.error(baseException.getCode(), baseException.getMessage());
        }

        return BaseResponse.error(BaseResponse.UNKNOWN_ERROR_CODE, e.getMessage());
    }
}
