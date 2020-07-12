package com.lutfly.design.patterns;

/**
 *  使用工厂方法模式设计一个程序来读取各种不同类型的图片格式，
 *  针对每一种图片格式都设 计一个图片读取器，
 *  如GIF图片读取器用于读取GIF格式的图片、JPG图片读取器用于读取JPG 格式的图片。需充分考虑系统的灵活性和可扩展性。
 *
 * @author lutong
 * @since 2020/6/29
 */
public class FactoryMethodTests {
    /*
    工厂方法模式(Factory Method Pattern)：定义一个用于创建对象的接口，让子类决定将哪一个
    类实例化。工厂方法模式让一个类的实例化延迟到其子类。工厂方法模式又简称为工厂模式
    (Factory Pattern)，又可称作虚拟构造器模式(Virtual Constructor Pattern)或多态工厂模式
    (Polymorphic Factory Pattern)。工厂方法模式是一种类创建型模式。
     */

    public static void main(String[] args) {
        PDFReaderFactory readerFactory = new PDFReaderFactory();
        Reader reader = readerFactory.getReader();
        String read = reader.read();
        System.out.println(read);
    }
}
interface ReaderFactory{
    Reader getReader();
}

class PDFReaderFactory implements ReaderFactory{

    @Override
    public Reader getReader() {
        return new PDFReader();
    }
}

class JPGReaderFactory implements ReaderFactory{

    @Override
    public Reader getReader() {
        return new JPGReader();
    }
}



interface Reader{
    String read();
}

class PDFReader implements Reader{

    @Override
    public String read() {
        return "PDF文件";
    }
}

class JPGReader implements Reader{

    @Override
    public String read() {
        return "JPG文件";
    }
}

