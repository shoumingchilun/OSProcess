package com.chilun.osprocessWithMemory.model;

/**
 * @auther 齿轮
 * @create 2022-11-13-19:32
 */
public class MemoryFactory_test {
    public static void main(String[] args) {
        Memory memory = MemoryFactory.getMemory();
        System.out.println(memory);
        memory = MemoryFactory.getMemory();
        System.out.println(memory);
        memory = MemoryFactory.getMemory();
        System.out.println(memory);
    }
}
