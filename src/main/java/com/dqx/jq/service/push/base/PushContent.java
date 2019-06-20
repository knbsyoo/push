package com.dqx.jq.service.push.base;

import com.dqx.jq.service.push.constant.PushConstant;
import lombok.Data;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 推送内容实体
 */
@Data
public class PushContent {

    /**
     * UUID
     */
    private String pushUuid;

    /**
     * 推送对象的类型：userId-用户
     */
    private String aliasType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作码
     */
    private Integer optCode;

    /**
     * 推送内容
     */
    private JSONObject data;

    /**
     * 类型：1-通知 2-消息
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 0-全部 1-Android 2-IOS
     */
    private Integer targetType;

    /**
     * 发送时间，不传则默认立即发送
     */
    private Date startTime;

    /**
     * 过期时间，不传则默认不过期
     */
    private Date expireTime;

    /**
     * 推送类型：0- customizedcast且不带file_id 1-broadcast 2-groupcast 3-filecast 4-customizedcast且file_id不为空
     * 不传默认0
     */
    private Integer castType;

    public PushContent() {
        this.pushUuid = UUID.randomUUID().toString();
        this.createTime = new Date();
    }

    public static PushContent sendNotification2Users(PushNotificationParams params) {
        PushContent pushContent = new PushContent();
        pushContent.setAliasType(PushConstant.ALIAS_TYPE_USER);
        pushContent.setType(PushConstant.TYPE_NOTIFICATION);
        pushContent.setTitle(params.getTitle());
        pushContent.setContent(params.getContent());
        setByPushParams(pushContent, params);
        return pushContent;
    }

    public static PushContent sendMsg2Users(PushMsgParams params) {
        PushContent pushContent = new PushContent();
        pushContent.setAliasType(PushConstant.ALIAS_TYPE_USER);
        pushContent.setType(PushConstant.TYPE_MSG);
        setByPushParams(pushContent, params);
        return pushContent;
    }

    private static PushContent setByPushParams(PushContent pushContent, PushParams params) {
        pushContent.setData(params.getData());
        pushContent.setOptCode(params.getOptCode());
        pushContent.setTargetType(params.getTargetType());
        pushContent.setStartTime(params.getStartTime());
        pushContent.setExpireTime(params.getExpireTime());
        if (null == params.getCastType()) {
            pushContent.setCastType(PushConstant.CAST_TYPE_CUSTOMIZEDCAST_NO_CONTAIN_FILE_ID);
        } else {
            pushContent.setCastType(params.getCastType());
        }
        if (null == params.getTargetType()) {
            pushContent.setTargetType(PushConstant.TARGET_TYPE_ALL);
        } else {
            pushContent.setTargetType(params.getTargetType());
        }
        return pushContent;
    }
}
