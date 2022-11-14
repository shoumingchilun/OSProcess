package com.chilun.osprocessWithMemory.model.service;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Memory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.NoAllocateItem;
import com.chilun.osprocessWithMemory.model.queueConnection.NewList;
import com.chilun.osprocessWithMemory.model.queueConnection.ReadyList;
import com.chilun.osprocessWithMemory.model.queueConnection.RunningList;
import com.chilun.osprocessWithMemory.model.queueConnection.TerminatedList;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;

import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-13-22:52
 */
public class OSService {
    public static void cleanAll() {
        NewList.setNull();
        ReadyList.setNull();
        RunningList.setNull();
        TerminatedList.setNull();
    }

    public static void printAll() {
        System.out.println(NewList.Sprint());
        System.out.println(ReadyList.Sprint());
        System.out.println(RunningList.Sprint());
        System.out.println(TerminatedList.Sprint());
    }

    public static List<Process> getNewList() {
        return NewList.getOnlyReady();
    }

    public static List<Process> getReadyList() {
        return ReadyList.getOnlyReady();
    }

    public static List<Process> getRunningList() {
        return RunningList.getOnlyReady();
    }

    public static List<Process> getTerminatedList() {
        return TerminatedList.getOnlyReady();
    }

    public static void addNew(Process process) {
        NewList.addProcess(process);
    }

    public static void addRunningFromReady(Process process) {
        boolean b = RunningList.addProcess(process);
        boolean b1 = ReadyList.deleteProcess(process);
        if (!b && b1) {
            throw new RuntimeException("OSService.addRunningFromReady失败了！");
        }
    }

    public static void addTerFromRunning(Process process) {
        boolean b = TerminatedList.addProcess(process);
        boolean b1 = RunningList.deleteProcess(process);
        if (!b && b1) {
            throw new RuntimeException("OSService.addTerFromRunning失败了！");
        }
    }

    //剩下需要的方法到时候再补
    public static void NewToReady() {

    }

    public static void OrderReadyListByPriority() {
        ReadyList.order();
    }

    public static void UpdateRunningList() {
        List<Process> processes = RunningList.get();
        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            int runTime = process.getRunTime();
            runTime--;
            process.setRunTime(runTime);
            if (runTime == 0) {
                addTerFromRunning(process);
            }
        }
    }

    public static boolean addableToRun() {
        return RunningList.addable();
    }

    public static void solveHungerByPromoteReadyPriority() {
        ReadyList.getOnlyReady().forEach(process -> {
            if (process.getPriority() != 0)
                process.setPriority(process.getPriority() - 1);
        });
    }

    public static void allocateMemory(Memory memory, Process process) {
        if (!memory.addable(process.getSize())){
            throw new RuntimeException("OSService.allocateMemory:塞不下了");
        }
        memory.order();
        List<NoAllocateItem> noAllocateTable = memory.getNoAllocateTable();

    }
}
