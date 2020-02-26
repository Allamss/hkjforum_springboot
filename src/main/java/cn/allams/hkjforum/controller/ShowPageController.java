package cn.allams.hkjforum.controller;

import cn.allams.hkjforum.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 展示页面
 * @author Allams
 *
 */
@Controller
public class ShowPageController {

    /**
     * 返回登陆界面
     * @return
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // TODO 使用SpringSecurity框架进行登录拦截
    /**
     * 返回发帖界面
     * @param session 判断用户是否登陆的HttpSession
     * @return
     */
    @GetMapping("/post")
    public String showPostPage(HttpSession session) {
        User user = (User)session.getAttribute("session_user");
        if (user == null) {
            return "redirect:/login";
        }
        return "post";
    }

    @GetMapping("/userdetails")
    public String showUserDetailsPage(HttpSession session) {
        User user = (User)session.getAttribute("session_user");
        if(user == null) {
            return "redirect:/login";
        }
        return "userdetails";
    }
}
