package com.chilun.osprocess.Model;

import java.util.Objects;

/**
 * @auther 齿轮
 * @create 2022-10-09-15:57
 */
public class MyProcess implements Comparable<MyProcess>{
    private String pid;
    private int runTime;
    private int priority;
    private int state;
    private String PCBPtr;
    private int IO;

    public MyProcess(String pid, int runTime, int priority, int state, String PCBPtr, int IO) {
        this.pid = pid;
        this.runTime = runTime;
        this.priority = priority;
        this.state = state;
        this.PCBPtr = PCBPtr;
        this.IO = IO;

        this.setPCBPtr(String.valueOf(hashCode()));
    }

    public int getIO() {
        return IO;
    }

    public void setIO(int IO) {
        this.IO = IO;
    }

    public MyProcess(String pid, int runTime, int priority, int state, String PCBPtr) {
        this.pid = pid;
        this.runTime = runTime;
        this.priority = priority;
        this.state = state;
        this.PCBPtr = PCBPtr;
    }

    public MyProcess() {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPCBPtr() {
        return PCBPtr;
    }

    public void setPCBPtr(String PCBPtr) {
        this.PCBPtr = PCBPtr;
    }

    @Override
    public String toString() {
        return (IO == 0 ? "" : "!") +
                pid +
                "\t剩余时间:" + runTime +
                "\t优先级:" + priority +
                "\tPCB指针:" + (this.state==0?"未分配":PCBPtr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyProcess)) return false;
        MyProcess process = (MyProcess) o;
        return runTime == process.runTime && priority == process.priority && state == process.state && IO == process.IO && Objects.equals(pid, process.pid) && Objects.equals(PCBPtr, process.PCBPtr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, runTime, priority, state, PCBPtr, IO);
    }

    @Override
    public int compareTo(MyProcess o) {
        return this.getPriority()-o.getPriority();
    }
}
