package com.lutfly.design.patterns;

/**
 * Sunny软件公司欲推出一款新的手机游戏软件，
 * 该软件能够支持Symbian、Android和 Windows Mobile等多个智能手机操作系统平台，
 * 针对不同的手机操作系统，该游戏软件提 供了不同的游戏操作控制(OperationController)类和游戏界面控制(InterfaceController)类，
 * 并提供相应的工厂类来封装这些类的初始化过程。软件要求具有较好的扩展性以支持新 的操作系统平台，为了满足上述需求，试采用抽象工厂模式对其进行设计。
 *
 * @author lutong
 * @since 2020/6/30
 */
public class AbstractFactoryTests {
    public static void main(String[] args) {
        GameFactory gameFactory = new SymbianFactory();
        InterfaceController anInterface = gameFactory.getInterface();
        OperationController operation = gameFactory.getOperation();
        anInterface.display();
        operation.play();
    }
}

interface GameFactory{

     OperationController getOperation();
     InterfaceController getInterface();
}

class AndroidFactory implements GameFactory{

    @Override
    public OperationController getOperation() {
        return new AndroidOperation();
    }

    @Override
    public InterfaceController getInterface() {
        return new AndroidInterface();
    }
}

class SymbianFactory implements GameFactory{

    @Override
    public OperationController getOperation() {
        return new SymbianOperation();
    }

    @Override
    public InterfaceController getInterface() {
        return new SymbianInterface();
    }
}

class WindowsFactory implements GameFactory{

    @Override
    public OperationController getOperation() {
        return new WindowsOperation();
    }

    @Override
    public InterfaceController getInterface() {
        return new WindowsInterface();
    }
}


interface InterfaceController{
    void display();
}

class SymbianInterface implements InterfaceController {

    @Override
    public void display() {
        System.out.println("Symbian display!");
    }
}
class AndroidInterface implements InterfaceController {

    @Override
    public void display() {
        System.out.println("Android display!");
    }
}
class WindowsInterface implements InterfaceController {
    @Override
    public void display() {
        System.out.println("Windows display!");
    }
}


interface OperationController {
    void play();
}
class SymbianOperation implements OperationController {

    @Override
    public void play() {
        System.out.println("Symbian play!");
    }
}
class AndroidOperation implements OperationController {

    @Override
    public void play() {
        System.out.println("Android play!");
    }
}
class WindowsOperation implements OperationController {
    @Override
    public void play() {
        System.out.println("Windows play!");
    }
}


