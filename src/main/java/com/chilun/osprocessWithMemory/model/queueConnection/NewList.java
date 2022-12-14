package com.chilun.osprocessWithMemory.model.queueConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;

/**
 * @auther 齿轮
 * @create 2022-11-13-21:55
 *
 * 使用桥接和装饰模式优化
 */
public class NewList {
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
        return "New队列：" + list.getOnlyReady();
    }

    public static void order(){
        list.order();
    }
}

//老代码：
//    private static List<Process> newList = new ArrayList<>();
//
//    public static List<Process> getOnlyReady() {
//        return Collections.unmodifiableList(newList);
//    }
//
//    public static void set(List<Process> newList) {
//        NewList.newList = newList;
//    }
//
//    public static void setNull() {
//        NewList.newList = new ArrayList<>();
//    }
//
//    public static boolean addProcess(Process process) {
//        for (Process p : newList) {
//            if (p.equals(process)) {
//                return false;
//            }
//        }
//        newList.add(process);
//        return true;
//    }
//
//    public static boolean deleteProcess(Process process) {
//        for (Process p : newList) {
//            if (p.equals(process)) {
//                newList.remove(p);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static String Sprint() {
//        return "New队列：" + newList;
//    }
