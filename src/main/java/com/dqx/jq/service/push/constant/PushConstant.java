package com.dqx.jq.service.push.constant;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 推送常量
 */
public class PushConstant {
    /**
     * Android App Key
     */
    public static final String ANDROID_APP_KEY = "5ca447b53fc19546da000175";
    /**
     * Android App Master Secret
     */
    public static final String ANDROID_APP_MASTER_SECRET = "da4apkqcjsa6xy9nv1g4fgg7h6bksql7";
    /**
     * IOS App Key
     */
    public static final String IOS_APP_KEY = "5ce755f8570df3a884000a47";
    /**
     * IOS App Master Secret
     */
    public static final String IOS_APP_MASTER_SECRET = "nrroruqkja73l5onylwsbq2wb6bnxeln";

    /**
     * Alias Max Size: 500
     */
    public static final int ALIAS_MAX_SIZE = 500;

    /**
     * Alias Type: User
     */
    public static final String ALIAS_TYPE_USER = "userId";

    /**
     * Push Type: 1- Notification
     */
    public static final int TYPE_NOTIFICATION = 1;
    /**
     * Push Type: 2- Msg
     */
    public static final int TYPE_MSG = 2;

    /**
     * Target Type: 0- All
     */
    public static final int TARGET_TYPE_ALL = 0;
    /**
     * Target Type: 0- Android
     */
    public static final int TARGET_TYPE_ADNROID = 1;
    /**
     * Target Type: 0- IOS
     */
    public static final int TARGET_TYPE_IOS = 2;

    /**
     * Cast Type: 0-customizedcast_no_contain_file_id
     */
    public static final int CAST_TYPE_CUSTOMIZEDCAST_NO_CONTAIN_FILE_ID = 0;
    /**
     * Cast Type: 1-broadcast
     */
    public static final int CAST_TYPE_BROADCAST = 1;

    /**
     * Redis Key Of Broadcast Times
     */
    public static final String BROADCAST_TIMES_REDIS_KEY = "service-push:broadcast-times";
}
