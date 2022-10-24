package com.chilun.osprocess.Model;

/**
 * @auther 齿轮
 * @create 2022-10-09-16:10
 */
public class MyProcessFactory {
    public static MyProcess newProcess(String pid, int runTime, int priority, int state, String PCBPtr){
        MyProcess process = new MyProcess(pid,runTime,priority,state,PCBPtr);
        process.setIO(0);//默认为cpu型
        return process;
    }
    public static MyProcess newProcess(String pid, int runTime, int priority, int state, String PCBPtr,int IO){
        MyProcess process = new MyProcess(pid,runTime,priority,state,PCBPtr,IO);
        return process;
    }
}
