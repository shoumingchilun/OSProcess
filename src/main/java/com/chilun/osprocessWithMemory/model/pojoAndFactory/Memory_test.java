package com.chilun.osprocessWithMemory.model.pojoAndFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @auther 齿轮
 * @create 2022-11-14-11:08
 */
public class Memory_test {
    public static void main(String[] args) {
        Memory memory = MemoryFactory.getMemory();
        System.out.println(memory.addable(10000));
        System.out.println(memory.addable(13));
        System.out.println(memory.addable(4 * 1024));

        List<NoAllocateItem> noAllocateTable = memory.getNoAllocateTable();
        noAllocateTable.remove(0);
        noAllocateTable.add(new NoAllocateItem(0, 1200));
        noAllocateTable.add(new NoAllocateItem(3000, 1000));
        System.out.println(memory);

        System.out.println(memory.addable(1200));
        System.out.println(memory.addable(1300));
        System.out.println(memory.addable(1000));
    }
}
