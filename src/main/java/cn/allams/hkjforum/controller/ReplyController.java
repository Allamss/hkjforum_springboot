package cn.allams.hkjforum.controller;

import cn.allams.hkjforum.entity.Reply;
import cn.allams.hkjforum.entity.User;
import cn.allams.hkjforum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 回复表现层
 * @author Allams
 */
@Controller
public class ReplyController {
    @Autowired
    ReplyService replyService;

    /**
     * 发送回复方法
     * @param postId 帖子id
     * @param reply 回复正文内容
     * @param bindingResult 校验器
     * @param model 模型
     * @param session session
     * @return 地址
     * @throws Exception 自定义异常
     */
    @PostMapping("/reply")
    public String sendReply(String postId, @Valid Reply reply, BindingResult bindingResult, Model model, HttpSession session)throws Exception
    {
        //获取错误信息
        if(bindingResult.hasErrors())
        {
            List<ObjectError> verificationErrors = bindingResult.getAllErrors();
            model.addAttribute("verificationErrors",verificationErrors);
            model.addAttribute("reply", reply);
            return "postdetails";
        }
        User session_user = (User)session.getAttribute("session_user");
        reply.setUserId(session_user.getId());
        reply.setAuthor(session_user.getUsername());

        reply.setPostId(Integer.valueOf(postId));
        replyService.sendReply(reply);
        return "redirect:/post/"+postId;
    }
}
