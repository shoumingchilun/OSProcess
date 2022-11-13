package com.chilun.osprocessWithMemory.model.queueConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;

/**
 * @auther 齿轮
 * @create 2022-11-13-21:58
 */
public class RunningList {
    public static int NUM_TRACKS = 8;//限制的道数
    public static int usedTracks = 0;
    private static List<Process> runningList = new ArrayList<>();

    public static List<Process> get() {
        return runningList;
    }

    public static List<Process> getOnlyReady() {
        return Collections.unmodifiableList(runningList);
    }


    public static boolean set(List<Process> runningList) {
        if (runningList.size() < NUM_TRACKS) {
            RunningList.runningList = runningList;
            usedTracks = runningList.size();
            return true;
        } else
            return false;
    }

    public static void setNull() {
        usedTracks = 0;
        RunningList.runningList = new ArrayList<>();
    }

    public static boolean addable() {
        return usedTracks < NUM_TRACKS;
    }

    public static boolean addProcess(Process process) {
        for (Process p : runningList) {
            if (p.equals(process)) {
                return false;
            }
        }
        if (addable()) {
            runningList.add(process);
            usedTracks++;
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteProcess(Process process) {
        for (Process p : runningList) {
            if (p.equals(process)) {
                runningList.remove(p);
                usedTracks--;
                return true;
            }
        }
        return false;
    }

    public static String Sprint() {
        return "Running队列（usedTracks-" + usedTracks + "）：" + runningList;
    }
}
