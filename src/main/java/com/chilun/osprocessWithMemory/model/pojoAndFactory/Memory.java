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
        return "主存剩余情况：" + noAllocateTable;
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

    public int getFirstPutableIndex(int size) {
        for (int i = 0; i < noAllocateTable.size(); i++) {
            if (size < noAllocateTable.get(i).getSize()) {
                return i;
            }
        }
        return -1;
    }

    public void recycleMemory(int beginSite, int size) {
        int endSite = beginSite + size;
        boolean canMergeWithPrior = false;
        int priorIndex = -1;
        boolean canMergeWithNext = false;
        int nextIndex = -1;
        for (int i = 0; i < noAllocateTable.size() && (!canMergeWithPrior || !canMergeWithNext); i++) {
            NoAllocateItem item1 = noAllocateTable.get(i);
            if (item1.getSize() + item1.getBeginSite() == beginSite) {
                priorIndex = i;
                canMergeWithPrior = true;
            }
            if (item1.getBeginSite() == endSite) {
                nextIndex = i;
                canMergeWithNext = true;
            }
        }
        if (canMergeWithNext && canMergeWithPrior) {
            NoAllocateItem item = noAllocateTable.get(priorIndex);
            item.setSize(item.getSize() + size + noAllocateTable.get(nextIndex).getSize());
            noAllocateTable.remove(nextIndex);
        } else if (canMergeWithPrior) {
            NoAllocateItem item = noAllocateTable.get(priorIndex);
            item.setSize(item.getSize() + size);
        } else if (canMergeWithNext) {
            NoAllocateItem item = noAllocateTable.get(nextIndex);
            item.setSize(item.getSize() + size);
            item.setBeginSite(beginSite);
        } else {
            noAllocateTable.add(new NoAllocateItem(beginSite, size));
        }
    }
}
