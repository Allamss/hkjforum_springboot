package cn.allams.hkjforum.controller;

import cn.allams.hkjforum.entity.User;
import cn.allams.hkjforum.exception.MyException;
import cn.allams.hkjforum.repository.UserRepository;
import cn.allams.hkjforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    // TODO 使用SpringSecurity框架实现用户登录
    // TODO 登陆学号输入过长，浏览器（将学号自动转化为带逗号的字符串导致？）Integer无法校验
    // TODO 开发完注册后密码使用md5加密存储
    /**
     *
     * @param form 页面传来的账号和密码信息
     * @param bindingResult 校验器
     * @return
     * @throws MyException
     */
    @PostMapping(value = "/login")
    public String login (@Valid User form, BindingResult bindingResult, Model model, HttpSession session)throws MyException {
        //获取校验错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> verificationErrors = bindingResult.getAllErrors();
            model.addAttribute("verificationErrors",verificationErrors);
            model.addAttribute("form", form);
            return "login";
        }
        try {
            User user = userService.login(form);
            session.setAttribute("session_user", user);
        }catch (MyException e){
            model.addAttribute("form", form);
            model.addAttribute("account_error",e.getMessage());
            return "login";
        }
        return "redirect:/index";
    }

    // TODO 可以用SpringSecurity框架实现用户退出登录
    /**
     * 登录退出方法
     * @param session 存放登陆用户信息的HttpSession
     * @return 返回主页
     */
    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.removeAttribute("session_user");
        return "redirect:/index";
    }

    @PostMapping("/updateuser")
    public String updateUser(String username, Model model, HttpSession session) throws MyException {
        if (session.getAttribute("session_user") == null) {
            return "redirect:/login";
        }
        if (username.length() < 2 || username.length() > 10) {
            model.addAttribute("verificationError", "用户名长度在2到10之间");
            return "userdetails";
        }
        User user = (User)session.getAttribute("session_user");
        userService.updateUser(user.getId(), username);
        user.setUsername(username);
        model.addAttribute("success_message", "用户名修改成功");
        session.setAttribute("session_user", user);
        System.out.println("哈哈哈哈哈哈哈哈哈");
        return "userdetails";
    }

}
