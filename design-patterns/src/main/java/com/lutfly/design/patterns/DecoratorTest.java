package com.lutfly.design.patterns;

import lombok.Data;

/**
 * Sunny软件公司欲开发了一个数据加密模块，可以对字符串进行加密。最简单的加密算法
 * 通过对字母进行移位来实现，同时还提供了稍复杂的逆向输出加密，还提供了更为高级
 * 的求模加密。用户先使用最简单的加密算法对字符串进行加密，如果觉得还不够可以对
 * 加密之后的结果使用其他加密算法进行二次加密，当然也可以进行第三次加密。试使用
 * 装饰模式设计该多重加密系统。
 *
 * Decorator 抽象装饰类可以省略 但会导致装饰体系结构不清晰 ，装饰者模式就像一个洋葱 抽象装饰类 是包装的开始  不应该删除
 *
 * Author	Date	Changes
 * fengy  2020/7/10 Created
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Weiyi data = new Weiyi();
        Decorator a = new NixiangshuchuDecorator(data);
        Decorator b = new WeiyiDecorator(a);
        Decorator c = new WeiyiDecorator(b);
        System.out.println(c.doEnCrypt("aaa"));
    }
}

interface EncryptMethod{
    String doEnCrypt(String data);
}

@Data
class Weiyi implements EncryptMethod{

    @Override
    public String doEnCrypt(String data) {
        return data+" 位移加密 ";
    }
}
@Data
class Nixiangshuchu implements EncryptMethod{

    @Override
    public String doEnCrypt(String data) {
        return data+" 逆向输出 ";
    }
}

class Decorator implements EncryptMethod {
    private EncryptMethod method;

    public Decorator(EncryptMethod method) {
        this.method = method;
    }

    @Override
    public String doEnCrypt(String data) {
        return method.doEnCrypt(data);
    }
}

class NixiangshuchuDecorator extends Decorator{
    public NixiangshuchuDecorator(EncryptMethod method) {
        super(method);
    }

    @Override
    public String doEnCrypt(String data) {
        return weiyi(super.doEnCrypt(data));
    }

    public String weiyi(String data) {
        return data+" 逆向输出 ";
    }
}

class WeiyiDecorator extends Decorator{
    public WeiyiDecorator(EncryptMethod method) {
        super(method);
    }

    @Override
    public String doEnCrypt(String data) {
        return weiyi(super.doEnCrypt(data));
    }

    public String weiyi(String data) {
        return data+" 位移加密 ";
    }
}
