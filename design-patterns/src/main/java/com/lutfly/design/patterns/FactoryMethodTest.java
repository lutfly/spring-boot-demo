package com.lutfly.design.patterns;


/**
 * 分析：有很多种类的畜牧场，如养马场用于养马，养牛场用于养牛，所以该实例用工厂方法模式比较适合。
 *
 * 对养马场和养牛场等具体工厂类，只要定义一个生成动物的方法 newAnimal() 即可。由于要显示马类和牛类等具体产品类的图像，所以它们的构造函数中用到了 JPanel、JLabd 和 ImageIcon 等组件，并定义一个 show() 方法来显示它们。
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        AnimalFactory factory = AnimalFactory.getFactory("niu");
        Animal animal = factory.getProduct();
        animal.bark();
    }

}

interface AnimalFactory {


    static AnimalFactory getFactory(String type){
        if (type.equals("ma")){
            return new HouseFactory();
        }else{
            return new CattleFactory();
        }
    }

    default Animal getProduct(){
        return this.getProduct();
    }
}

class HouseFactory implements AnimalFactory {

    @Override
    public Animal getProduct() {
        return new House();
    }
}

class CattleFactory implements AnimalFactory {

    @Override
    public Animal getProduct() {
        return  new Cattle();
    }
}

interface Animal{
    void bark();
}

class House implements Animal{
    @Override
    public void bark() {
        System.out.println("马叫");
    }
}


class Cattle implements Animal{
    @Override
    public void bark() {
        System.out.println("牛叫");
    }
}