package com.chilun.osprocessWithMemory.model.queueConnection;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import com.chilun.osprocessWithMemory.model.queueConnection.NewList;

/**
 * @auther 齿轮
 * @create 2022-11-13-22:36
 */
public class NewList_test {
    public static void main(String[] args) {
        Process process = ProcessFactory.CreateProcess("1", 5, 3, 12);
        NewList.addProcess(process);
        System.out.println(NewList.Sprint());

        Process process1 = ProcessFactory.CreateProcess("1", 5, 3, 12);
        NewList.addProcess(process1);
        System.out.println(NewList.Sprint());

        Process process2 = ProcessFactory.CreateProcess("2", 5, 3, 12);
        Process process3 = ProcessFactory.CreateProcess("1", 5, 3, 12);
        NewList.addProcess(process2);
        NewList.deleteProcess(process3);
        System.out.println(NewList.Sprint());

        NewList.setNull();
        System.out.println(NewList.Sprint());
//        NewList.getOnlyReady().add(process);

    }
}
