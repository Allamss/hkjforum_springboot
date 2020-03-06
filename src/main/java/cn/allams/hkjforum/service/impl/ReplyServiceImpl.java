package cn.allams.hkjforum.service.impl;

import cn.allams.hkjforum.entity.Post;
import cn.allams.hkjforum.entity.Reply;
import cn.allams.hkjforum.repository.PostRepository;
import cn.allams.hkjforum.repository.ReplyRepository;
import cn.allams.hkjforum.service.ReplyService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * 评论逻辑层实现类
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
    @Cacheable(value="replys", key="'postId4replys'+#postId", unless = "#result==null", cacheManager = "initRedisCacheManager")
    public String findReplyByPostId(Integer postId) {
        List<Reply> list = replyRepository.findReplyByPostId(postId);
        String listJson = JSON.toJSONString(list);
        return listJson;
    }
}
