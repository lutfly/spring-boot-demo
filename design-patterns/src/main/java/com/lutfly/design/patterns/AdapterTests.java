package com.lutfly.design.patterns;

/**
 * Sunny软件公司OA系统需要提供一个加密模块，
 * 将用户机密信息（如口令、邮箱等）加 密之后再存储在数据库中，
 * 系统已经定义好了数据库操作类。为了提高开发效率，
 * 现需 要重用已有的加密算法，这些算法封装在一些由第三方提供的类中，
 * 有些甚至没有源代 码。试使用适配器模式设计该加密模块，
 * 实现在不修改现有类的基础上重用第三方加密 方法。
 *
 *
 * Author	Date	Changes
 * fengy  2020/7/7 Created
 */
public class AdapterTests {
    /*
        适配器模式(Adapter Pattern)：将一个接口转换成客户希望的另一个接口，使接口不兼容的那
        些类可以一起工作，其别名为包装器(Wrapper)。适配器模式既可以作为类结构型模式，也可
        以作为对象结构型模式。

        适配器让那些由于接口不兼容而不能交互的类可以一起工作
     */
    public static void main(String[] args) {
        Target target = new Adapter(new ConcreteEncrypt(), new DataSourceOperator());
        target.save("zhangsan");
    }

}

interface Target {
    void save(String id);
}

class Adapter implements Target {

    private Encrypt encrypt;

    private DataSourceOperator operator;

    public Adapter(Encrypt encrypt, DataSourceOperator operator) {
        this.encrypt = encrypt;
        this.operator = operator;
    }

    @Override
    public void save(String userName) {
        int encrypt = this.encrypt.encrypt(userName);
        operator.save(encrypt);
    }
}

interface Encrypt {
    int encrypt(String name);
}

class ConcreteEncrypt implements Encrypt {

    @Override
    public int encrypt(String name) {
        return name.hashCode();
    }
}

class DataSourceOperator {

    public void save(int userName) {
        System.out.println(userName);
    }
}