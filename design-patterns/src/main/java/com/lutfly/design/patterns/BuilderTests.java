package com.lutfly.design.patterns;

/**
 *
 *
 * @author lutong
 * @since 2020/7/3
 */
public class BuilderTests {
    public static void main(String[] args) {
//        Director director = new Director();
        System.out.println(new HummerBuilder().construct());
    }

}

class Director{
    public Actor construct(ActorBuilder ab){
        ab.buildHead();
        ab.buildBody();
        ab.buildFoot();
        return ab.getResult();
    }
}

abstract class ActorBuilder {

    protected Actor actor=new Actor();

    abstract void buildHead();

    abstract void buildBody();

    abstract void buildFoot();

    Actor getResult(){
        return actor;
    }

    public Actor construct(){
        this.buildHead();
        this.buildBody();
        this.buildFoot();
        return this.getResult();
    }
}




class Actor {
    String head;
    String body;
    String foot;

    @Override
    public String toString() {
        return "Actor{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", foot='" + foot + '\'' +
                '}';
    }
}

class HummerBuilder extends ActorBuilder {

    @Override
    void buildHead() {
        actor.head = "hummer head";
    }

    @Override
    void buildBody() {
        actor.body= "hummer body";
    }

    @Override
    void buildFoot() {
        actor.foot = "hummer foot";
    }
}

class HeroBuilder extends ActorBuilder {

    @Override
    void buildHead() {
        actor.head = "hero head";
    }

    @Override
    void buildBody() {
        actor.head = "hero body";
    }

    @Override
    void buildFoot() {
        actor.foot = "hero foot";

    }
}

