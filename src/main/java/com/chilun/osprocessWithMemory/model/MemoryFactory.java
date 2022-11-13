package com.chilun.osprocessWithMemory.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @auther 齿轮
 * @create 2022-11-13-19:29
 */
public class MemoryFactory {
    private static Constructor<Memory> constructor;
    private static Memory memory;
    static {
        Class<Memory> c = Memory.class;
        ClassLoader classLoader = c.getClassLoader();
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
