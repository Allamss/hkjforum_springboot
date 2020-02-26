package cn.allams.hkjforum.service;


import cn.allams.hkjforum.HkjforumApplication;
import cn.allams.hkjforum.entity.User;
import cn.allams.hkjforum.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = HkjforumApplication.class)
public class UserControllerTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Test
    public void testLogin(){
        User user = new User();
        user.setAccount(1);
        user.setPassword("123");
        System.out.println(userService.login(user).getId());
    }

    @Test
    public void testUpdateUser() {
        userService.updateUser(1, "aaa");

    }
}
