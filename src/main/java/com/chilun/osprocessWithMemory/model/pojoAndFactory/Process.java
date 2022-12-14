package com.chilun.osprocessWithMemory.model.pojoAndFactory;

import java.util.Objects;

/**
 * @auther 齿轮
 * @create 2022-11-13-18:43
 */
public class Process {
    private String pid;
    private int runTime;
    private int priority;
    private int size;
    private int beginSite;
    private String PCBPtr;

    private Process() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBeginSite() {
        return beginSite;
    }

    public void setBeginSite(int beginSite) {
        this.beginSite = beginSite;
    }

    public String getPCBPtr() {
        return PCBPtr;
    }

    public void setPCBPtr(String PCBPtr) {
        this.PCBPtr = PCBPtr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Process)) return false;
        Process process = (Process) o;
        return pid.equals(process.pid);
    }

    @Override
    public String toString() {
        return  pid  +
                "：剩余时间 =" + runTime +
                ", priority=" + priority +
                ", size=" + size +
                ", beginSite=" + beginSite +
                ", PCBPtr='" + PCBPtr +
                '\n';
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, runTime, priority,  size, beginSite, PCBPtr);
    }
}
