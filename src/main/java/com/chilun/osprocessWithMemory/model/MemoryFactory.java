package com.chilun.osprocessWithMemory.model;

import java.lang.reflect.Constructor;

/**
 * @auther 齿轮
 * @create 2022-11-13-19:29
 *
 * 使用：单例模式——懒汉式创建对象
 */
public class MemoryFactory {
    private static Constructor<Memory> constructor;
    private static Memory memory;
    static {
        Class<Memory> c = Memory.class;
        try {
            constructor = c.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        constructor.setAccessible(true);
        try {
            memory = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Memory getMemory(){
        return memory;
    }
}
