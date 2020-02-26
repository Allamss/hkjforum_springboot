package cn.allams.hkjforum.service.impl;

import cn.allams.hkjforum.entity.Post;
import cn.allams.hkjforum.entity.Reply;
import cn.allams.hkjforum.repository.PostRepository;
import cn.allams.hkjforum.repository.ReplyRepository;
import cn.allams.hkjforum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * 回复逻辑层实现类
 * @author Allams
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public void sendReply(Reply reply) {
        replyRepository.save(reply);
        Post post = postRepository.findById(reply.getPostId()).orElse(null);
        if (post != null) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            Date date = Date.from(zonedDateTime.toInstant());
            post.setUpdateTime(date);
            postRepository.save(post);
        }
    }

    @Override
    public List<Reply> findReplyByPostId(Integer postId) {
        List<Reply> list = replyRepository.findReplyByPostId(postId);
        return list;
    }
}
