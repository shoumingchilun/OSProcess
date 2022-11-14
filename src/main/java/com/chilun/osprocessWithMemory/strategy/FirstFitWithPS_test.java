package com.chilun.osprocessWithMemory.strategy;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.MemoryFactory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
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
        for (int i = 0; i < 10; i++) {
            double pid = Math.random() * 100 + 100;
            Process process = ProcessFactory.CreateProcess("" + pid, (int) (Math.random() * 14) + 5, (int) (Math.random() * 8), (int) (Math.random() * 500) + 1);
            OSService.addNew(process);
        }
        OSService.printAll();
        FirstFitWithPS f = new FirstFitWithPS();
        for (int i = 0; i < 25; i++) {
            f.nextStep();
            OSService.printAll();
            MemoryFactory.getMemory().order();
            System.out.println(MemoryFactory.getMemory());
            System.out.println();
            System.out.println();
        }
    }
}
