package con.lutfly.sbdAcceptTransactional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String test()  {
//        return "hello laidian!";
        return "你好 来电!";

    }

    @GetMapping("/ping")
    public String ping() {
        return "pong!";
    }
}
