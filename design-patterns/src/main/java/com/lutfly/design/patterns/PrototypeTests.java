package com.lutfly.design.patterns;

import lombok.Data;

/**
 * 原型模式
 *
 * @author lutong
 * @since 2020/7/3
 */
public class PrototypeTests {

    /*
    原型模式(Prototype Pattern)：使用原型实例指定创建对象的种类，并且通过拷贝这些
原型创建新的对象。原型模式是一种对象创建型模式。
     */

    public static void main(String[] args) throws CloneNotSupportedException {
        A zhangsan = new A("zhangsan", 12, new B("zhangsan"));
        Object clone = zhangsan.clone();
        System.out.println(zhangsan);
        System.out.println(clone);
        zhangsan.name="asd";
        zhangsan.age=21;
        zhangsan.classNum.name="lining";
        System.out.println(zhangsan);
        System.out.println(clone);
    }
}


class A implements Cloneable{
    public A(String name, int age, B classNum) {
        this.name = name;
        this.age = age;
        this.classNum = classNum;
    }

    public String name;
    public int  age;
    public B classNum;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classNum=" + classNum +
                '}';
    }
}
class B{
    public String name;

    public B(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                '}';
    }
}