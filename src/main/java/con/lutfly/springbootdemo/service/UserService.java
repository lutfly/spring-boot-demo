package con.lutfly.springbootdemo.service;

import con.lutfly.springbootdemo.annotation.AnnotationTest;
import con.lutfly.springbootdemo.entity.User;
import con.lutfly.springbootdemo.repository.UserRepository;
import con.lutfly.springbootdemo.thread.AsyncTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @AnnotationTest
    public void test()  {
        System.out.println("update 之前: ");
        User user = userRepository.getOne(1l);
        System.out.println(user);
        int i = new Random().nextInt(100);
        user.setAge(i);
        System.out.println("我要改成: " + i);
        userRepository.save(user);
    }



//    @Transactional
//    public void test()  {
//        System.out.println("update 之前: ");
//        User user = userRepository.getOne(1l);
//        System.out.println(user);
//
//        int i = new Random().nextInt();
//        user.setAge(i);
//        System.out.println("我要改成: " + i);
//
//        userRepository.save(user);
//        asyncTest.sync();
//    }

}
