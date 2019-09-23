package con.lutfly.sbdAcceptTransactional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String test() throws InterruptedException {
        return "hi!";
    }

    @GetMapping("/ping")
    public String ping() throws InterruptedException {
        return "pong!";
    }
}
