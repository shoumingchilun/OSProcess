package com.chilun.osprocessWithMemory.model.pojoAndFactory;


/**
 * @auther 齿轮
 * @create 2022-11-13-19:03
 */
public class ProcessFactory_test {
    //不知道为什么用@Test会报错，就直接用main了。
    public static void main(String[] args) {
        Process process = ProcessFactory.CreateProcess("测试进程", 18, 4, 30);
        System.out.println(process);
    }
}
