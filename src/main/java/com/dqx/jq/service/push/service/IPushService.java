package com.dqx.jq.service.push.service;

import com.dqx.jq.service.push.base.PushContent;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description:
 */
public interface IPushService {

    /**
     * 通过友盟推送通知，消息给指定用户列表
     */
    void sendMsgOrNotification2Users(PushContent pushContent, String targetIds);

    /**
     * 广播通知、信息给所有用户
     */
    void broadcastMessageOrNotifications2Users(PushContent pushContent);
}
