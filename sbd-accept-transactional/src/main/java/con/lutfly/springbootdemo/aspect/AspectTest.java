package con.lutfly.springbootdemo.aspect;

import con.lutfly.springbootdemo.thread.AsyncTest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE-1)
public class AspectTest {

    @Autowired
    private AsyncTest asyncTest;

    @AfterReturning("@annotation(con.lutfly.springbootdemo.annotation.AnnotationTest)")
    public void afterReturning(JoinPoint point) throws InterruptedException {
        point.getTarget();
        asyncTest.async();
        System.out.println("我是 AfterReturning");
    }

//    @Before("@annotation(con.lutfly.springbootdemo.annotation.AnnotationTest)")
//    public void before() {
//        System.out.println("我是 before");
//    }
//
//    @After("@annotation(con.lutfly.springbootdemo.annotation.AnnotationTest)")
//    public void after() {
//        System.out.println("我是 after");
//    }

//    @Around("@annotation(con.lutfly.springbootdemo.annotation.AnnotationTest)")
//    public void around(ProceedingJoinPoint point) throws Throwable {
//
//        System.out.println("我是 around before");
//        Object o = point.proceed();
//        System.out.println("我是 around after");
//    }

//    @Override
//    public int getOrder() {
//        return 1;
//    }
}
