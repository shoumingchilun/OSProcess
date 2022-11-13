package com.chilun.osprocessWithMemory.model.queueConnection;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;

/**
 * @auther 齿轮
 * @create 2022-11-13-22:11
 */
public class RunningList_test {
    public static void main(String[] args) {
        Process process = ProcessFactory.CreateProcess("1", 5, 3, 12);
        RunningList.addProcess(process);
        System.out.println(RunningList.Sprint());
        Process process1 = ProcessFactory.CreateProcess("1", 5, 3, 12);
        RunningList.addProcess(process1);
        System.out.println(RunningList.Sprint());
        Process process2 = ProcessFactory.CreateProcess("2", 5, 3, 12);
        RunningList.addProcess(process2);
        System.out.println(RunningList.Sprint());
        Process process3 = ProcessFactory.CreateProcess("3", 5, 3, 12);
        RunningList.addProcess(process3);
        System.out.println(RunningList.Sprint());
        RunningList.deleteProcess(process);
        System.out.println(RunningList.Sprint());
        RunningList.setNull();
        System.out.println(RunningList.Sprint());
        //RunningList.getOnlyReady().add(process);不可修改
    }
}
