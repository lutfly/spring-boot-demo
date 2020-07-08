package com.lutfly.design.patterns;

/**
 * Sunny软件公司欲开发一个数据转换工具，
 * 可以将数据库中的数据转换成多种文件格式，
 * 例如txt、xml、pdf等格式，同时该工具需要支持多种不同的数据库。
 * 试使用桥接模式对 其进行设计。
 *
 * Author	Date	Changes
 * fengy  2020/7/9 Created
 */
public class BridgeTest {
    /*
    分析两个维度：  文件格式 多种数据库类型
     */
    public static void main(String[] args) {
        DataConvert convert = new PdfConvert();
        convert.setDataSource(new Redis());
        convert.convert();
    }
}

abstract class DataConvert {
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract void convert();
}

class TxtConvert extends DataConvert {

    @Override
    public void convert() {
        String data = dataSource.getData();
        System.out.println("convert "+data+" to TXT !");
    }
}

class XmlConvert extends DataConvert {

    @Override
    public void convert() {
        String data = dataSource.getData();
        System.out.println("convert "+data+" to XML !");
    }
}

class PdfConvert extends DataConvert {

    @Override
    public void convert() {
        String data = dataSource.getData();
        System.out.println("convert "+data+" to PDF !");
    }
}

interface DataSource{
    String getData();
}

class Mysql implements DataSource{

    @Override
    public String getData() {
        return "Mysql Data";
    }
}

class Redis implements DataSource{

    @Override
    public String getData() {
        return "Redis Data";
    }
}