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
 *
 * 使用桥接和装饰模式优化
 */
public class ReadyList {
    private static BaseList list = new BaseList();

    public static List<Process> getOnlyReady() {
        return list.getOnlyReady();
    }

    public static void set(List<Process> newList) {
        list.set(newList);
    }

    public static void setNull() {
        list.setNull();
    }

    public static boolean addProcess(Process process) {
        return list.addProcess(process);
    }

    public static boolean deleteProcess(Process process) {
        return list.deleteProcess(process);
    }

    public static String Sprint() {
        return "Ready队列：" + list.getOnlyReady();
    }

    public static void order(){
        list.order();
    }
}

//老代码：
//    private static List<Process> readyList = new ArrayList<>();
//
//    public static List<Process> getOnlyReady() {
//        return Collections.unmodifiableList(readyList);
//    }
//
//    public static void set(List<Process> readyList) {
//        ReadyList.readyList = readyList;
//    }
//
//    public static void setNull() {
//        ReadyList.readyList = new ArrayList<>();
//    }
//
//    //主存判断能否加入的逻辑请写在策略类中
//    public static boolean addProcess(Process process) {
//        for (Process p : readyList) {
//            if (p.equals(process)) {
//                return false;
//            }
//        }
//        readyList.add(process);
//        return true;
//    }
//
//    public static boolean deleteProcess(Process process) {
//        for (Process p : readyList) {
//            if (p.equals(process)) {
//                readyList.remove(p);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static String Sprint() {
//        return "Ready队列：" + readyList;
//    }
