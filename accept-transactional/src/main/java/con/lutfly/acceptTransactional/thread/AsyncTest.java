package con.lutfly.acceptTransactional.thread;

import con.lutfly.acceptTransactional.entity.User;
import con.lutfly.acceptTransactional.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {

    @Autowired
    private UserRepository userRepository;

    @Async
    public void async() throws InterruptedException {
        System.out.println("异步 update 之后: ");
        User user = userRepository.getOne(1l);
        System.out.println(user);
    }

    public void sync() {
        System.out.println("同步 update 之后: ");
        User user = userRepository.getOne(1l);
        System.out.println(user);
    }
}
