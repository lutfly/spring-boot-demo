package com.lutfly.acceptTransactional.thread;

import com.lutfly.acceptTransactional.entity.User;
import com.lutfly.acceptTransactional.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {

    @Autowired
    private UserRepository userRepository;

    @Async
    public void async()  {
        System.out.println("异步 update 之后: ");
        User user = userRepository.getOne(1L);
        System.out.println(user);
    }

    public void sync() {
        System.out.println("同步 update 之后: ");
        User user = userRepository.getOne(1L);
        System.out.println(user);
    }
}
