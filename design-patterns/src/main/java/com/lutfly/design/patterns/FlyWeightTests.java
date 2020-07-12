package com.lutfly.design.patterns;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Sunny软件公司欲开发一个多功能文档编辑器，在文本文档中可以插入图片、动画、视频
 * 等多媒体资料，为了节约系统资源，相同的图片、动画和视频在同一个文档中只需保存
 * 一份，但是可以多次重复出现，而且它们每次出现时位置和大小均可不同。试使用享元
 * 模式设计该文档编辑器。
 *
 *
 * Author	Date	Changes
 * fengy  2020/7/13 Created
 */
public class FlyWeightTests {
    /*
    享元模式(Flyweight Pattern)：运用共享技术有效地支持大量细粒度对象的复用。系统只使
    用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用。由于
    享元模式要求能够共享的对象必须是细粒度对象，因此它又称为轻量级模式，它是一种
    对象结构型模式。

    当系统中存在大量相同或者相似的对象时，享元模式是一种较好的解决方案，它通过共享技
    术实现相同或相似的细粒度对象的复用，从而节约了内存空间，提高了系统性能。

    将一个对象属性分为固定不变属性和可变属性 从而对对象进行拆分
     */
    public static void main(String[] args) {
        ResourceFactory  factory= ResourceFactory.getInstance();
        factory.getResource("pic","aaa").display(1,11);
        factory.getResource("video","bbb").display(2,22);
        factory.getResource("pic","aaa").display(3,33);
        factory.getResource("video","bbb").display(4,44);
        factory.getResource("pic","aaa").display(5,55);
        factory.getResource("animation","aaa").display(6,66);
        factory.getResource("pic","aaa").display(7,77);
        factory.getResource("animation","aaa").display(8,88);

    }

}
class ResourceFactory{

    private ResourceFactory() {}
    private static final class Factory{
        private static final ResourceFactory resourceFactory=new ResourceFactory();
    }
    public static ResourceFactory getInstance(){
        return Factory.resourceFactory;
    }

    private static Map<String,Map<String ,Resource>> typePool = new HashMap<>();
    private static Map<String,Resource> picPool = new HashMap<>();
    private static Map<String,Resource> videoPool = new HashMap<>();
    private static Map<String,Resource> animationPool = new HashMap<>();
    static {
        typePool.put("pic",picPool);
        typePool.put("video",videoPool);
        typePool.put("animation",animationPool);
    }

    public Resource getResource(String type, String name){
        Map<String, Resource> resourceMap = typePool.get(type);
        Resource resource = resourceMap.get(name);
        if (resource==null){
            switch (type) {
                case "pic":
                    resource = new Pic(type, name);
                    picPool.put(name, resource);
                    break;
                case "video":
                    resource = new Video(type, name);
                    videoPool.put(name, resource);
                    break;
                case "animation":
                    resource = new Animation(type, name);
                    videoPool.put(name, resource);
                    break;
            }
        }
        return resource;
    }

}


@Data
abstract class Resource {

    private String type;

    private String name;

    private Resource(){}
    public Resource(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public void display(Integer location, Integer size) {
        System.out.println(this.hashCode()+"   " +type+" - "+name+" 显示在：{"+location+"},大小 "+size);
    }
}
class Pic extends Resource{

    public Pic(String type, String name) {
        super(type, name);
    }


}
class Animation extends Resource{

    public Animation(String type, String name) {
        super(type, name);
    }


}
class Video extends Resource{

    public Video(String type, String name) {
        super(type, name);
    }

}