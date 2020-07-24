package com.lutfly.design.patterns;

/**
 * 使用简单工厂模式设计一个可以创建不同几何形状(如圆形、方形和三角形等)的绘图 工具，
 * 每个几何图形都具有绘制draw()和擦除erase()两个方法，要求在绘制不支持的几何 图形时，提示一个UnSupportedShapeException。
 * Simple Factory Pattern
 * @author lutong
 * @since 2020/6/29
 */
public class SimpleFactoryTests {

    /*
        简单工厂模式(Simple Factory Pattern)：定义一个工厂类，它可以根据参数的不同返回不同类的
        实例，被创建的实例通常都具有共同的父类。因为在简单工厂模式中用于创建实例的方法是
        静态(static)方法，因此简单工厂模式又被称为静态工厂方法(Static Factory Method)模式，它属
        于类创建型模式。

        简单工厂模式实现了对象创建和使用的分离。
     */
    public static void main(String[] args) {
        ShapeTool tool = ShapeTool.getShapeTool("12");
        tool.draw();
    }

}
class UnSupportedShapeException extends RuntimeException{}

interface ShapeTool {
    void draw() throws UnSupportedShapeException;

    void erase();

    static ShapeTool getShapeTool(String type){
        switch (type) {
            case "1":
                return new Triangle();
            case "2":
                return new Circle();
            case "3":
                return new Square();
            default:
                throw new UnSupportedShapeException();
        }
    }
}

class Triangle implements ShapeTool {

    @Override
    public void draw() throws UnSupportedShapeException {
        System.out.println("△");
    }

    @Override
    public void erase() {

    }
}

class Circle implements ShapeTool {

    @Override
    public void draw() throws UnSupportedShapeException {
        System.out.println("○");
    }

    @Override
    public void erase() {

    }
}

class Square implements ShapeTool {

    @Override
    public void draw() throws UnSupportedShapeException {
        System.out.println("□");
    }

    @Override
    public void erase() {

    }
}