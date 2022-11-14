package com.chilun.osprocessWithMemory.model.pojoAndFactory;

import java.lang.reflect.Constructor;

/**
 * @auther 齿轮
 * @create 2022-11-13-18:48
 */
public class ProcessFactory {
    private static Constructor<Process> constructor;
    private static Process process;

    static {
        Class<Process> c = Process.class;
        try {
            constructor = c.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        constructor.setAccessible(true);
    }

    public static Process CreateProcess() {
        Process process = null;
        try {
            process = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (process != null) {
            //初始化进程类的元素
            process.setBeginSite(-1);
            process.setPCBPtr(null);
            process.setPCBPtr("未分配");
        }
        return process;
    }

    public static Process CreateProcess(String pid, int runTime, int priority, int size) {
        Process process = CreateProcess();
        process.setPid(pid);
        process.setRunTime(runTime);
        process.setPriority(priority);
        process.setSize(size);
        return process;
    }
}
