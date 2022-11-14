package com.chilun.osprocessWithMemory.strategy;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.service.OSService;

import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-13-23:04
 */
public class FirstFitWithPS {

    public void nextStep() {
        //running到终止队列
        OSService.UpdateRunningList();
        //Ready队列到Running队列：算法——优先级
        OSService.OrderReadyListByPriority();
        List<Process> readyList = OSService.getReadyList();
        while (readyList.size() > 0 && OSService.addableToRun()) {
            Process process = readyList.get(0);
            OSService.addRunningFromReady(process);
        }
        //对Ready队列中剩余的进程升级优先级
        OSService.solveHungerByPromoteReadyPriority();

        //将new队列加入到Ready队列


        //接下来要补全有关主存的一系列方法
    }
}
