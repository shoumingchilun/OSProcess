package com.chilun.osprocessWithMemory.model.pojoAndFactory;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @auther 齿轮
 * @create 2022-11-13-19:24
 */
public class Memory {
    private static final BigDecimal TOTAL_SIZE = new BigDecimal(4*1024);//4个G
    private Map<BigDecimal, BigDecimal> noAllocateTable;//记录起始位置和终止位置

    private Memory() {
    }

    public Map<BigDecimal, BigDecimal> getNoAllocateTable() {
        return noAllocateTable;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "noAllocateTable=" + noAllocateTable +
                '}';
    }

    public void setNoAllocateTable(Map<BigDecimal, BigDecimal> noAllocateTable) {
        this.noAllocateTable = noAllocateTable;
    }
}
