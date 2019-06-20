package com.dqx.jq.service.push.task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created By JianBin.Liu on 2019/6/3
 * Description: 任务池
 */
@Slf4j
public class TaskPool {

    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    /**
     * 任务提交到缓存线程池中
     *
     * @param task
     */
    public static void execute(Runnable task) {
        threadPoolExecutor.submit(task);
        log.info("【线程池任务】线程池中线程数：" + threadPoolExecutor.getPoolSize());
        log.info("【线程池任务】队列中等待执行的任务数：" + threadPoolExecutor.getQueue().size());
        log.info("【线程池任务】已执行完任务数：" + threadPoolExecutor.getCompletedTaskCount());
    }

}
