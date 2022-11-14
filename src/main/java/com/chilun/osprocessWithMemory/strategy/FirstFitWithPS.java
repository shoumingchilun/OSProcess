package com.chilun.osprocessWithMemory.strategy;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Memory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.MemoryFactory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.queueConnection.NewList;
import com.chilun.osprocessWithMemory.model.service.OSService;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-13-23:04
 */
public class FirstFitWithPS {
    public void nextStep() {
        //running到终止队列
        OSService.UpdateRunningList(MemoryFactory.getMemory());
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
        OSService.OrderNewListByPriority();
        List<Process> newList = NewList.getOnlyReady();
        List<Process> addedList = new ArrayList<>();//存储已加入的进程
        for (int i = 0; i < newList.size(); i++) {
            Process process = newList.get(i);
            if (OSService.addableToMemory(MemoryFactory.getMemory(), process)) {
                try {
                    OSService.allocateMemory(MemoryFactory.getMemory(), process);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                addedList.add(process);
                process.setPCBPtr("" + process.hashCode());
            }
        }
        //已实现将可加入的process加到内存中，接下来的将已加入的process从NewList移动到ReadyList
        OSService.addListToReadyFromNew(addedList);
    }
}
