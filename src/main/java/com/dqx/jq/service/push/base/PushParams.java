package com.dqx.jq.service.push.base;

import lombok.Data;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created By JianBin.Liu on 2019/6/18
 * Description: 推送的入参
 */
@Data
public class PushParams {

    /**
     * 操作码
     */
    @NotNull
    private Integer optCode;

    /**
     * 数据
     */
    @NotNull
    private JSONObject data;

    /**
     * 0-全部 1-Android 2-IOS，不传默认0-全部
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
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 目标对象ids
     */
    private String targetIds;

    /**
     * 推送类型：0- customizedcast且不带file_id 1-broadcast
     * 不传默认0
     */
    private Integer castType;
}
