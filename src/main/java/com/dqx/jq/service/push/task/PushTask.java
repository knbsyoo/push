package com.dqx.jq.service.push.task;

import com.dqx.jq.service.push.base.PushContent;
import com.dqx.jq.service.push.constant.PushConstant;
import com.dqx.jq.service.push.helper.PushHelper;
import com.dqx.jq.service.push.service.IPushService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 友盟推送任务
 */
@Slf4j
@Data
public class PushTask implements Runnable {

    @Autowired
    IPushService pushService;

    @Autowired
    /**
     * 推送对象
     */
    private List<String> targetIds;

    /**
     * 推送的实体
     */
    private PushContent pushContent;

    /**
     * 执行推送任务
     */
    @Override
    public void run() {
        String targetIdsStr = StringUtils.join(targetIds, ",");
        PushHelper helper = PushHelper.getInstance();
        if (pushContent.getCastType() == PushConstant.CAST_TYPE_BROADCAST) {//广播
            broadcast(helper);
        } else {//自定义播
            customizedcast(helper, targetIdsStr);
        }
    }

    /**
     * 广播
     *
     * @param helper
     */
    private void broadcast(PushHelper helper) {
        switch (pushContent.getTargetType()) {
            case PushConstant.TARGET_TYPE_ADNROID: {//Android
                helper.sendAndroidBroadcast(pushContent);
                break;
            }
            case PushConstant.TARGET_TYPE_IOS: {//IOS
                helper.sendIOSBroadcast(pushContent);
                break;
            }
            default: {//IOS,Android
                helper.sendAndroidBroadcast(pushContent);
                helper.sendIOSBroadcast(pushContent);
                break;
            }
        }
    }

    /**
     * 自定义播
     *
     * @param helper
     * @param targetIds
     */
    private void customizedcast(PushHelper helper, String targetIds) {
        switch (pushContent.getTargetType()) {
            case PushConstant.TARGET_TYPE_ADNROID: {
                helper.sendCustomizedcast(targetIds, pushContent);
                break;
            }
            case PushConstant.TARGET_TYPE_IOS: {
                helper.sendIOSCustomizedcast(pushContent, targetIds);
                break;
            }
            default: {
                helper.sendCustomizedcast(targetIds, pushContent);
                helper.sendIOSCustomizedcast(pushContent, targetIds);
                break;
            }
        }
    }
}
