package com.dqx.jq.service.push.umeng;

import com.dqx.jq.service.push.constant.PushConstant;
import com.dqx.jq.service.push.umeng.android.AndroidBroadcast;
import com.dqx.jq.service.push.umeng.android.AndroidCustomizedcast;
import com.dqx.jq.service.push.umeng.ios.IOSBroadcast;
import com.dqx.jq.service.push.umeng.ios.IOSCustomizedcast;

public class Demo {
    private String timestamp = null;
    private PushClient client = new PushClient();

//    public static void main(String[] args) {
//        // TODO set your appkey and master secret here
//        Demo demo = new Demo();
//        try {
////            demo.sendAndroidBroadcast("!1","2");
////            demo.sendAndroidCustomizedcast("!","2","31231");
//            demo.sendIOSBroadcast();
////            demo.sendIOSCustomizedcast();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    /**
     * 安卓-广播
     *
     * @throws Exception
     */
    public void sendAndroidBroadcast(String title, String context) throws Exception {
        AndroidBroadcast broadcast = new AndroidBroadcast(PushConstant.ANDROID_APP_KEY, PushConstant.ANDROID_APP_MASTER_SECRET);
        broadcast.setTicker(title);//必填，通知栏提示文字，title和ticker都设置为title
        broadcast.setTitle(title);// 必填，通知标题
        broadcast.setText(context);//通知文字描述
        broadcast.goAppAfterOpen();
        broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        broadcast.setProductionMode();
        // Set customized fields
        broadcast.setExtraField("test", "helloworld");//扩展字段
        client.send(broadcast);
    }

    /**
     * 安卓-自定义播
     *
     * @throws Exception
     */
    public void sendAndroidCustomizedcast(String title, String context, String alias) throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(PushConstant.ANDROID_APP_KEY, PushConstant.ANDROID_APP_MASTER_SECRET);
        // TODO Set your alias here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        customizedcast.setAlias(alias, PushConstant.ALIAS_TYPE_USER);
        customizedcast.setTicker(title);
        customizedcast.setTitle(title);
        customizedcast.setText(context);
        customizedcast.goAppAfterOpen();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        client.send(customizedcast);
    }

    /**
     * IOS-广播
     *
     * @throws Exception
     */
    public void sendIOSBroadcast() throws Exception {
        IOSBroadcast broadcast = new IOSBroadcast(PushConstant.IOS_APP_KEY, PushConstant.IOS_APP_MASTER_SECRET);
        broadcast.setAlert("IOS 广播测试");
        broadcast.setBadge(0);
        broadcast.setSound("default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        broadcast.setTestMode();
        // Set customized fields
        broadcast.setCustomizedField("test", "helloworld");//自定义字段
        client.send(broadcast);
    }

    /**
     * IOS-自定义播
     *
     * @throws Exception
     */
    public void sendIOSCustomizedcast() throws Exception {
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(PushConstant.IOS_APP_KEY, PushConstant.IOS_APP_MASTER_SECRET);
        // TODO Set your alias and alias_type here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        customizedcast.setAlias("alias", "alias_type");
        customizedcast.setAlert("IOS 个性化测试");
        customizedcast.setBadge(0);
        customizedcast.setSound("default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        customizedcast.setTestMode();
        client.send(customizedcast);
    }
}
