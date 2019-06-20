package com.dqx.jq.service.push.umeng.ios;

import com.dqx.jq.service.push.umeng.IOSNotification;

public class IOSBroadcast extends IOSNotification {
    public IOSBroadcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "broadcast");

    }
}
