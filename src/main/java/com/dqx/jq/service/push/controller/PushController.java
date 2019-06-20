package com.dqx.jq.service.push.controller;

import com.dqx.jq.common.response.BaseResponse;
import com.dqx.jq.service.push.base.PushContent;
import com.dqx.jq.service.push.base.PushMsgParams;
import com.dqx.jq.service.push.base.PushNotificationParams;
import com.dqx.jq.service.push.constant.PushConstant;
import com.dqx.jq.service.push.exception.BusinessException;
import com.dqx.jq.service.push.service.IPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 友盟推送服务控制器
 */
@Slf4j
@RestController
public class PushController {

    @Autowired
    private IPushService pushService;

    /**
     * 通过自定义播推送通知给指定用户列表
     *
     * @return
     */
    @PostMapping("/send-notifications")
    public BaseResponse sendNotification2Users(@RequestBody @Validated PushNotificationParams pushNotificationParams) throws BusinessException {
        if (StringUtils.isEmpty(pushNotificationParams.getTargetIds()))
            throw BusinessException.VALIDATE("targetIds不能为空");
        pushService.sendMsgOrNotification2Users(PushContent.sendNotification2Users(pushNotificationParams), pushNotificationParams.getTargetIds());
        return BaseResponse.success("推送任务已经提交");
    }

    /**
     * 推送通知给指定用户列表
     *
     * @return
     */
    @PostMapping("/send-messages")
    public BaseResponse sendMsg2Users(@RequestBody @Validated PushMsgParams pushMsgParams) throws BusinessException {
        if (StringUtils.isEmpty(pushMsgParams.getTargetIds())) throw BusinessException.VALIDATE("targetIds不能为空");
        pushService.sendMsgOrNotification2Users(PushContent.sendMsg2Users(pushMsgParams), pushMsgParams.getTargetIds());
        return BaseResponse.success("推送任务已经提交");
    }

    /**
     * 广播通知给所有的用户
     *
     * @return
     */
    @PostMapping("/broadcast-notifications")
    public BaseResponse broadcastNotification2Users(@RequestBody @Validated PushNotificationParams pushMsgParams) {
        pushMsgParams.setCastType(PushConstant.CAST_TYPE_BROADCAST);
        pushService.broadcastMessageOrNotifications2Users(PushContent.sendNotification2Users(pushMsgParams));
        return BaseResponse.success("推送任务已经提交");
    }

    /**
     * 广播消息给所有的用户
     *
     * @return
     */
    @PostMapping("/broadcast-messages")
    public BaseResponse broadcastMessage2Users(@RequestBody @Validated PushMsgParams pushMsgParams) {
        pushMsgParams.setCastType(PushConstant.CAST_TYPE_BROADCAST);
        pushService.broadcastMessageOrNotifications2Users(PushContent.sendMsg2Users(pushMsgParams));
        return BaseResponse.success("推送任务已经提交");
    }
}
