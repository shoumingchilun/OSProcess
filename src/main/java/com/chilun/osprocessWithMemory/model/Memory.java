package com.chilun.osprocessWithMemory.model;

import java.util.Map;

/**
 * @auther 齿轮
 * @create 2022-11-13-19:24
 */
public class Memory {
    private static final int TOTAL_SIZE = 4 * 1024;//4个G
    private Map<Integer, Integer> noAllocateTable;//记录起始位置和终止位置

    public Memory() {
    }

    public Map<Integer, Integer> getNoAllocateTable() {
        return noAllocateTable;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "noAllocateTable=" + noAllocateTable +
                '}';
    }

    public void setNoAllocateTable(Map<Integer, Integer> noAllocateTable) {
        this.noAllocateTable = noAllocateTable;
    }
}
