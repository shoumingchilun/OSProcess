package com.chilun.osprocessWithMemory.model.queueConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;

/**
 * @auther 齿轮
 * @create 2022-11-13-21:57
 *
 * 主存判断能否加入的逻辑请写在策略类中
 */
public class ReadyList {
    private static List<Process> readyList = new ArrayList<>();

    public static List<Process> getOnlyReady() {
        return Collections.unmodifiableList(readyList);
    }

    public static void set(List<Process> readyList) {
        ReadyList.readyList = readyList;
    }

    public static void setNull() {
        ReadyList.readyList = new ArrayList<>();
    }

    //主存判断能否加入的逻辑请写在策略类中
    public static boolean addProcess(Process process) {
        for (Process p : readyList) {
            if (p.equals(process)) {
                return false;
            }
        }
        readyList.add(process);
        return true;
    }

    public static boolean deleteProcess(Process process) {
        for (Process p : readyList) {
            if (p.equals(process)) {
                readyList.remove(p);
                return true;
            }
        }
        return false;
    }

    public static String Sprint() {
        return "Ready队列：" + readyList;
    }
}
