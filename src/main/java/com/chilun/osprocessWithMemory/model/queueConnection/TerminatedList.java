package com.chilun.osprocessWithMemory.model.queueConnection;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-13-22:46
 */
public class TerminatedList {
    private static List<Process> terminatedList = new ArrayList<>();

    public static List<Process> getOnlyReady() {
        return Collections.unmodifiableList(terminatedList);
    }


    public static void setNull() {
        TerminatedList.terminatedList = new ArrayList<>();
    }

    public static boolean addProcess(Process process) {
        for (Process p : terminatedList) {
            if (p.equals(process)) {
                return false;
            }
        }
        terminatedList.add(process);
        return true;
    }

    public static boolean deleteProcess(Process process) {
        for (Process p : terminatedList) {
            if (p.equals(process)) {
                terminatedList.remove(p);
                return true;
            }
        }
        return false;
    }

    public static String Sprint() {
        return "Terminated队列：" + terminatedList;
    }
}
