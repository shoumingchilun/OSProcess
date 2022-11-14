package com.chilun.osprocessWithMemory.model.queueConnection;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;

/**
 * @auther 齿轮
 * @create 2022-11-13-22:43
 */
public class ReadyList_test {
    public static void main(String[] args) {
        Process process = ProcessFactory.CreateProcess("1", 5, 3, 12);
        ReadyList.addProcess(process);
        System.out.println(ReadyList.Sprint());

        Process process1 = ProcessFactory.CreateProcess("1", 5, 3, 12);
        ReadyList.addProcess(process1);
        System.out.println(ReadyList.Sprint());

        Process process2 = ProcessFactory.CreateProcess("2", 5, 3, 12);
        Process process3 = ProcessFactory.CreateProcess("1", 5, 3, 12);
        ReadyList.addProcess(process2);
        ReadyList.deleteProcess(process3);
        System.out.println(ReadyList.Sprint());

        ReadyList.setNull();
        System.out.println(ReadyList.Sprint());

        ReadyList.addProcess(ProcessFactory.CreateProcess("1",1,2,1));
        ReadyList.addProcess(ProcessFactory.CreateProcess("2",1,3,1));
        ReadyList.addProcess(ProcessFactory.CreateProcess("3",1,4,1));
        ReadyList.addProcess(ProcessFactory.CreateProcess("4",1,1,1));
        ReadyList.addProcess(ProcessFactory.CreateProcess("5",1,5,1));
        ReadyList.addProcess(ProcessFactory.CreateProcess("6",1,7,1));
        ReadyList.order();
        System.out.println(ReadyList.Sprint());
    }
}
