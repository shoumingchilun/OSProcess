package com.chilun.osprocessWithMemory.model.pojoAndFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @auther 齿轮
 * @create 2022-11-13-19:24
 */
public class Memory {
    public static final int TOTAL_SIZE = 4 * 1024;//4个G
    private List<NoAllocateItem> noAllocateTable;//记录起始位置和未分分区大小

    private Memory() {
    }

    public List<NoAllocateItem> getNoAllocateTable() {
        return noAllocateTable;
    }

    @Override
    public String toString() {
        return "主存使用情况：" + noAllocateTable;
    }

    public void setNoAllocateTable(List<NoAllocateItem> noAllocateTable) {
        this.noAllocateTable = noAllocateTable;
    }

    public void order() {
        Collections.sort(noAllocateTable);
    }

    public boolean addable(int size) {
        order();
        NoAllocateItem noAllocateItem = noAllocateTable.get(noAllocateTable.size() - 1);
        return noAllocateItem.getSize() >= size;
    }


}
