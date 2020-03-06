package cn.allams.hkjforum.service;

import cn.allams.hkjforum.entity.Reply;
import cn.allams.hkjforum.exception.MyException;

import java.util.List;

/**
 * 回复逻辑层接口
 * @author Allams
 */
public interface ReplyService {
    /**
     * 发送帖子方法
     * @param reply 发送的帖子
     */
    void sendReply(Reply reply);

    /**
     * 根据帖子找回复
     * @param postId 帖子id
     */
    String findReplyByPostId(Integer postId);

}
