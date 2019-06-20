package com.dqx.jq.service.push.base;

import com.dqx.jq.service.push.constant.PushConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 推送的通知入参
 */
@Data
public class PushNotificationParams extends PushParams {

    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * 内容
     */
    @NotNull
    private String content;

    /**
     * 类型：1-通知 2-消息
     */
    private Integer type = PushConstant.TYPE_NOTIFICATION;

}
