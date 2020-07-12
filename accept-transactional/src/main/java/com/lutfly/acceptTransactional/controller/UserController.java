package com.lutfly.acceptTransactional.controller;

import com.lutfly.acceptTransactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public void test()  {
        userService.test();
    }

}
