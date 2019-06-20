package com.dqx.jq.service.push.helper;

import com.dqx.jq.service.push.base.PushContent;
import com.dqx.jq.service.push.constant.PushConstant;
import com.dqx.jq.service.push.umeng.PushClient;
import com.dqx.jq.service.push.umeng.android.AndroidBroadcast;
import com.dqx.jq.service.push.umeng.android.AndroidCustomizedcast;
import com.dqx.jq.service.push.umeng.ios.IOSBroadcast;
import com.dqx.jq.service.push.umeng.ios.IOSCustomizedcast;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import static com.dqx.jq.service.push.umeng.AndroidNotification.DisplayType.MESSAGE;
import static com.dqx.jq.service.push.umeng.AndroidNotification.DisplayType.NOTIFICATION;

/**
 * Created By JianBin.Liu on 2019/6/14
 * Description: 推送帮助类
 */
@Slf4j
public class PushHelper {

    private static PushHelper helper;
    private PushClient client = new PushClient();

    private PushHelper() {
    }

    public static PushHelper getInstance() {
        if (null == helper) helper = new PushHelper();
        return helper;
    }

    /**
     * 安卓-自定义播
     *
     * @param alias
     * @param pushContent
     * @throws Exception
     */
    public void sendCustomizedcast(String alias, PushContent pushContent) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            AndroidCustomizedcast cast = new AndroidCustomizedcast(PushConstant.ANDROID_APP_KEY, PushConstant.ANDROID_APP_MASTER_SECRET);
            cast.setAlias(alias, pushContent.getAliasType());
            cast.setExtraField("data", pushContent.getData().toString());
            cast.setExtraField("optCode", pushContent.getOptCode().toString());
            cast.setProductionMode();
            if (null != pushContent.getStartTime()) cast.setStartTime(dateFormat.format(pushContent.getStartTime()));
            if (null != pushContent.getExpireTime()) cast.setExpireTime(dateFormat.format(pushContent.getExpireTime()));
            if (pushContent.getType() == PushConstant.TYPE_NOTIFICATION) {
                cast.setTicker(pushContent.getTitle());
                cast.setTitle(pushContent.getTitle());
                cast.setText(pushContent.getContent());
                cast.goAppAfterOpen();
                cast.setDisplayType(NOTIFICATION);
            } else {
                cast.setDisplayType(MESSAGE);
            }
            client.send(cast);
        } catch (Exception e) {
            log.info("广播-Android，pushUuid：" + pushContent.getPushUuid() + "，推送对象：" + alias + "，提交任务失败，发送异常：" + e.getMessage());
            log.error(e.getMessage());
        }
    }

    /**
     * 安卓-广播
     *
     * @throws Exception
     */
    public void sendAndroidBroadcast(PushContent pushContent) {
        try {
            AndroidBroadcast cast = new AndroidBroadcast(PushConstant.ANDROID_APP_KEY, PushConstant.ANDROID_APP_MASTER_SECRET);
            if (pushContent.getType() == PushConstant.TYPE_NOTIFICATION) {
                cast.setTicker(pushContent.getTitle());
                cast.setTitle(pushContent.getTitle());
                cast.setText(pushContent.getContent());
                cast.goAppAfterOpen();
                cast.setDisplayType(NOTIFICATION);
            } else {
                cast.setDisplayType(MESSAGE);
            }
            cast.setTestMode();
            cast.setExtraField("data", pushContent.getData().toString());
            cast.setExtraField("optCode", pushContent.getOptCode().toString());
            cast.setCustomField(pushContent.getData());
            client.send(cast);
        } catch (Exception e) {
            log.info("广播-Android，pushUuid：" + pushContent.getPushUuid() + "提交任务失败，发送异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * IOS-自定义播
     *
     * @param alias
     * @throws Exception
     */
    public void sendIOSCustomizedcast(PushContent pushContent, String alias) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            IOSCustomizedcast cast = new IOSCustomizedcast(PushConstant.IOS_APP_KEY, PushConstant.IOS_APP_MASTER_SECRET);
            if (pushContent.getType() == PushConstant.TYPE_NOTIFICATION) {
                JSONObject alert = new JSONObject();
                alert.put("title", pushContent.getTitle());
                alert.put("content", pushContent.getContent());
                cast.setAlert(alert.toString());
                cast.setContentAvailable(0);// 0-通知 1-静默推送
            } else {
                cast.setContentAvailable(1);
            }
            cast.setAlias(alias, pushContent.getAliasType());
            cast.setBadge(0);
            cast.setSound("default");
            cast.setProductionMode();
//            cast.setTestMode();
            cast.setCustomizedField("data", pushContent.getData().toString());
            cast.setCustomizedField("optCode", pushContent.getOptCode().toString());
            if (null != pushContent.getStartTime()) cast.setStartTime(dateFormat.format(pushContent.getStartTime()));
            if (null != pushContent.getExpireTime()) cast.setExpireTime(dateFormat.format(pushContent.getExpireTime()));
            client.send(cast);
        } catch (Exception e) {
            log.info("广播-IOS，pushUuid：" + pushContent.getPushUuid() + "，推送对象：" + alias + "，提交任务失败，发送异常：" + e.getMessage());
            log.error(e.getMessage());
        }
    }


    /**
     * IOS-广播
     *
     * @throws Exception
     */
    public void sendIOSBroadcast(PushContent pushContent) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            IOSBroadcast cast = new IOSBroadcast(PushConstant.IOS_APP_KEY, PushConstant.IOS_APP_MASTER_SECRET);
            if (pushContent.getType() == PushConstant.TYPE_NOTIFICATION) {
                JSONObject alert = new JSONObject();
                alert.put("title", pushContent.getTitle());
                alert.put("content", pushContent.getContent());
                cast.setAlert(alert.toString());
                cast.setContentAvailable(0);// 0-通知 1-静默推送
            } else {
                cast.setContentAvailable(1);
            }
            cast.setBadge(0);
            cast.setSound("default");
            cast.setProductionMode();
//            cast.setTestMode();
            cast.setCustomizedField("data", pushContent.getData().toString());
            cast.setCustomizedField("optCode", pushContent.getOptCode().toString());
            if (null != pushContent.getStartTime()) cast.setStartTime(dateFormat.format(pushContent.getStartTime()));
            if (null != pushContent.getExpireTime()) cast.setExpireTime(dateFormat.format(pushContent.getExpireTime()));
            client.send(cast);
        } catch (Exception e) {
            log.info("广播-IOS，pushUuid：" + pushContent.getPushUuid() + "提交任务失败，发送异常：" + e.getMessage());
            //todo 可以记录日志
            log.error(e.getMessage());
        }
    }
}
