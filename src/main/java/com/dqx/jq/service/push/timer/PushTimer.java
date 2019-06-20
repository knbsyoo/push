package com.dqx.jq.service.push.timer;

import com.dqx.jq.service.push.constant.PushConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created By JianBin.Liu on 2019/6/19
 * Description:
 */
@Slf4j
@Component
public class PushTimer {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 刷新友盟的推送广播次数，每天重置
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void flushBroadcastTimes() {
        redisTemplate.opsForValue().set(PushConstant.BROADCAST_TIMES_REDIS_KEY, 0, 1, TimeUnit.DAYS);
    }
}
