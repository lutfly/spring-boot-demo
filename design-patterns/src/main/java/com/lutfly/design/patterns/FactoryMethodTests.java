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

