package com.lutfly.design.patterns;

/**
 * Initialization Demand Holder
 *
 * @author lutong
 * @since 2020/6/30
 */
public class IoDHTests {

    /*
    根据JVM规范，Java类只会被初始化一次；在初始化的时候会对被初始化的类对应的Class对象加对象锁（相当于synchronized (MyClass.class)）

     */
    private IoDHTests(){}

    private static class SingleTon{
        private final static IoDHTests singleTon=new IoDHTests();
    }

    public static IoDHTests getInstance(){
        return SingleTon.singleTon;
    }

    public void sing(){
        System.out.println("aaaaaaaa");
    }
}

class test{
    public static void main(String[] args) {
        IoDHTests instance = IoDHTests.getInstance();
        instance.sing();
    }
}
