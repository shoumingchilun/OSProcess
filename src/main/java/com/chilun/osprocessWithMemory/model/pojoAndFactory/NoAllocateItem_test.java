package com.chilun.osprocessWithMemory.model.pojoAndFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-14-11:22
 */
public class NoAllocateItem_test {
    public static void main(String[] args) {
        NoAllocateItem noAllocateItem = new NoAllocateItem(0,1024);
        NoAllocateItem noAllocateItem1 = new NoAllocateItem(1025,12);
        NoAllocateItem noAllocateItem2 = new NoAllocateItem(1500,230);
        NoAllocateItem noAllocateItem3 = new NoAllocateItem(2000,2000);

        List<NoAllocateItem> list = new ArrayList<>();
        list.add(noAllocateItem);
        list.add(noAllocateItem1);
        list.add(noAllocateItem2);
        list.add(noAllocateItem3);
        Collections.sort(list);

        System.out.println(list);
    }
}
