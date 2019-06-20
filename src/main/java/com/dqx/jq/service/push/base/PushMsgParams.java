package com.dqx.jq.service.push.base;

import com.dqx.jq.service.push.constant.PushConstant;
import lombok.Data;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 推送的消息入参
 */
@Data
public class PushMsgParams extends PushParams {

    /**
     * 类型：1-通知 2-消息
     */
    private Integer type = PushConstant.TYPE_MSG;
}
