package com.lutfly.design.patterns;

/**
 *
 *
 * @author lutong
 * @since 2020/6/30
 */
public class IoDHTests {

    private IoDHTests(){};

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
