package com.chilun.osprocessWithMemory.strategy;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.MemoryFactory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import com.chilun.osprocessWithMemory.model.queueConnection.NewList;
import com.chilun.osprocessWithMemory.model.queueConnection.ReadyList;
import com.chilun.osprocessWithMemory.model.queueConnection.RunningList;
import com.chilun.osprocessWithMemory.model.service.OSService;

/**
 * @auther 齿轮
 * @create 2022-11-13-23:13
 */
public class FirstFitWithPS_test {
    public static void main(String[] args) throws Exception {
        NewList.addProcess(ProcessFactory.CreateProcess("1", 5, 1, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("2", 4, 2, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("3", 2, 3, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("4", 2, 4, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("5", 2, 5, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("6", 2, 6, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("7", 2, 7, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("8", 2, 1, 120));
        NewList.addProcess(ProcessFactory.CreateProcess("9", 2, 2, 120));
        OSService.printAll();
        FirstFitWithPS f = new FirstFitWithPS();
        for (int i = 0; i < 9; i++) {
            f.nextStep();
            OSService.printAll();
            MemoryFactory.getMemory().order();
            System.out.println(MemoryFactory.getMemory());
            System.out.println();
            System.out.println();
        }
    }
}
