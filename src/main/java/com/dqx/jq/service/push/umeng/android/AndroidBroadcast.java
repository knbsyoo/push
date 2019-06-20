package com.dqx.jq.service.push.umeng.android;

import com.dqx.jq.service.push.umeng.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
    public AndroidBroadcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "broadcast");
    }
}
