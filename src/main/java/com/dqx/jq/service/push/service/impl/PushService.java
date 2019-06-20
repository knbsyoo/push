package com.dqx.jq.service.push.service.impl;

import com.dqx.jq.service.push.base.PushContent;
import com.dqx.jq.service.push.constant.PushConstant;
import com.dqx.jq.service.push.exception.BusinessException;
import com.dqx.jq.service.push.exception.BusinessExceptionCode;
import com.dqx.jq.service.push.service.IPushService;
import com.dqx.jq.service.push.task.PushTask;
import com.dqx.jq.service.push.task.TaskPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 推送业务
 */
@Slf4j
@Service
public class PushService implements IPushService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送静默消息、通知给指定用户列表
     * 采用自定义播中的alias方式
     *
     * @param pushContent
     * @param targetIds
     */
    @Override
    public void sendMsgOrNotification2Users(PushContent pushContent, String targetIds) {
        List<String> targetIdList = Arrays.asList(targetIds.split(","));
        if (targetIdList.size() > PushConstant.ALIAS_MAX_SIZE) {//友盟alias值，长度最多允许500，并且要求入参时候以英文逗号分隔,切割成多组，提交到任务线程池中。
            int taskNumber = (targetIdList.size() + PushConstant.ALIAS_MAX_SIZE - 1) / PushConstant.ALIAS_MAX_SIZE;
            for (int i = 0; i < taskNumber; i++) {
                List<String> curTargetIds = targetIdList.subList(i * PushConstant.ALIAS_MAX_SIZE, (i + 1) * PushConstant.ALIAS_MAX_SIZE);
                PushTask task = new PushTask();
                task.setTargetIds(curTargetIds);
                task.setPushContent(pushContent);
                TaskPool.execute(task);
            }
        } else {
            PushTask task = new PushTask();
            task.setTargetIds(targetIdList);
            task.setPushContent(pushContent);
            TaskPool.execute(task);
        }
    }

    /**
     * 广播静默消息、通知给所有用户
     *
     * @param pushContent
     */
    public void broadcastMessageOrNotifications2Users(PushContent pushContent) throws BusinessException {
        //获取当日广播剩余次数
        Object timesObj = redisTemplate.opsForValue().get(PushConstant.BROADCAST_TIMES_REDIS_KEY);
        log.info("timesObj:" + timesObj);
        if (null == timesObj) {
            long now = new Date().getTime();
            //当天24点的时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long tomorrow = 0L;
            try {
                tomorrow = dateFormat.parse(dateFormat.format(now)).getTime() + 24 * 60 * 60 * 1000;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            redisTemplate.opsForValue().set(PushConstant.BROADCAST_TIMES_REDIS_KEY, 10, tomorrow - now, TimeUnit.MILLISECONDS);
        } else {
            Integer times = new Integer(timesObj.toString());
            if (times == 0) throw new BusinessException(BusinessExceptionCode.BROADCAST_MAXIMUM_TIMES);
        }
        PushTask task = new PushTask();
        task.setPushContent(pushContent);
        TaskPool.execute(task);
        //更新当日广播剩余次数
        Integer times = new Integer(redisTemplate.opsForValue().get(PushConstant.BROADCAST_TIMES_REDIS_KEY).toString());
        log.info("times:" + times);
        long timeout = redisTemplate.getExpire(PushConstant.BROADCAST_TIMES_REDIS_KEY, TimeUnit.MILLISECONDS);
        redisTemplate.opsForValue().set(PushConstant.BROADCAST_TIMES_REDIS_KEY, times - 1, timeout, TimeUnit.MILLISECONDS);
        log.info("times2:" + redisTemplate.opsForValue().get(PushConstant.BROADCAST_TIMES_REDIS_KEY).toString());
        //累加
    }
}
