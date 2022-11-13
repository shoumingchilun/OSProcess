package com.chilun.osprocessWithMemory.strategy;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import com.chilun.osprocessWithMemory.model.queueConnection.RunningList;
import com.chilun.osprocessWithMemory.model.service.OSService;

/**
 * @auther 齿轮
 * @create 2022-11-13-23:13
 */
public class FirstFitWithPS_test {
    public static void main(String[] args) {
        RunningList.addProcess(ProcessFactory.CreateProcess("1", 5, 3, 12));
        RunningList.addProcess(ProcessFactory.CreateProcess("2", 4, 3, 12));
        RunningList.addProcess(ProcessFactory.CreateProcess("3", 2, 3, 12));
        FirstFitWithPS f = new FirstFitWithPS();
        for (int i = 0; i < 6; i++) {
            f.nextStep();
            OSService.printAll();
        }
    }
}
