package cn.allams.hkjforum.controller;

import cn.allams.hkjforum.entity.Post;
import cn.allams.hkjforum.entity.Reply;
import cn.allams.hkjforum.entity.User;
import cn.allams.hkjforum.exception.MyException;
import cn.allams.hkjforum.service.PostService;
import cn.allams.hkjforum.service.ReplyService;
import cn.allams.hkjforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 帖子表现层
 * @author Allams
 */
@Controller
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;
    /**
     * 发送帖子方法
     * @param post 需要发送的帖子
     * @param bindingResult 校验器
     * @param model 模型
     * @param session session
     * @return
     */
    @PostMapping(value = "/post")
    public String sendPost(@Valid Post post, BindingResult bindingResult, Model model, HttpSession session)throws Exception {
        //获取错误信息
        if(bindingResult.hasErrors()){
            List<ObjectError> verificationErrors = bindingResult.getAllErrors();
            model.addAttribute("verificationErrors",verificationErrors);
            return "post";
        }
        try {
            User session_user = (User)session.getAttribute("session_user");
            if (session_user == null) {
                return "redirect:/login";
            }
            post.setUserId(session_user.getId());
            postService.sendPost(post);
        }catch (MyException e){
            model.addAttribute("error_message", e.getMessage());
            model.addAttribute("post",post);
            return "post";
        }
        model.addAttribute("success_message", "发帖成功！");
        return "redirect:/index";
    }

    /**
     * 返回分页帖子列表
     * @param curPage 查找页数
     * @param model 模型
     * @return 分页数据
     */
    @GetMapping(value = {"/","/index"})
    public String findAllPost(@RequestParam(value = "curPage",defaultValue = "1") Integer curPage, Model model){
        Page<Post> page = postService.findAllPost(curPage);
        model.addAttribute("postList",page);
        return "index";
    }

    /**
     * 查询帖子详情
     * @param id 查询帖子id
     * @param model 模型
     * @return 帖子详情
     */
    @GetMapping("/post/{id}")
    public String findPostById(@PathVariable("id") Integer id, Model model){
        Post post = postService.findPostById(id);
        String username = userService.findUserById(post.getUserId()).getUsername();
        model.addAttribute("username", username);
        model.addAttribute("post",post);
        List<Reply> replyList = replyService.findReplyByPostId(id);
        model.addAttribute("replyList", replyList);

        return "postdetails";
    }
}
