package cn.allams.hkjforum.service;

import cn.allams.hkjforum.HkjforumApplication;
import cn.allams.hkjforum.entity.Post;
import cn.allams.hkjforum.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HkjforumApplication.class)
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    public void testFindUserById(){
        System.out.println(postService.findPostById(1).getTopic());
    }

    @Test
    public void testSendPost(){
        Post post = new Post();
        post.setTopic("test6");
        post.setUserId(1);
        post.setContent("test666");
        postService.sendPost(post);
    }

    @Test
    public void testFindPostById() {
        Post post = postService.findPostById(7);
        System.out.println(post.getTopic());
    }



}
