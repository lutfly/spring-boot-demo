package com.lutfly.design.patterns;

/**
 * 使用简单工厂模式设计一个可以创建不同几何形状(如圆形、方形和三角形等)的绘图 工具，
 * 每个几何图形都具有绘制draw()和擦除erase()两个方法，要求在绘制不支持的几何 图形时，提示一个UnSupportedShapeException。
 * Simple Factory Pattern
 * @author lutong
 * @since 2020/6/29
 */
public class SimpleFactoryTests {

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
        if (type.equals("1")){
            return new Triangle();
        }else if (type.equals("2")){
            return new Circle();
        }else if (type.equals("3")){
            return new Square();
        }else{
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