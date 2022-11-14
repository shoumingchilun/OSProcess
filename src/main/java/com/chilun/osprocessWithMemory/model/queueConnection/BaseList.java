package com.chilun.osprocessWithMemory.model.queueConnection;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-14-9:53
 */
public class BaseList {
    private List<Process> List = new ArrayList<>();

    public BaseList() {
    }

    public void order(){
        List.sort((Object o1, Object o2) -> {
            Process P1 = (Process) o1;
            Process P2 = (Process) o2;
            return P1.getPriority() - P2.getPriority();
        });
    }

    public boolean addProcess(Process process) {
        for (Process p : List) {
            if (p.equals(process)) {
                return false;
            }
        }
        List.add(process);
        return true;
    }

    public boolean deleteProcess(Process process) {
        for (Process p : List) {
            if (p.equals(process)) {
                List.remove(p);
                return true;
            }
        }
        return false;
    }

    public String Sprint() {
        return "基本队列：" + List;
    }

    public void setNull() {
        List = new ArrayList<>();
    }

    public List<Process> getOnlyReady() {
        return Collections.unmodifiableList(List);
    }

    public void set(List<Process> newList) {
        List = newList;
    }

}
