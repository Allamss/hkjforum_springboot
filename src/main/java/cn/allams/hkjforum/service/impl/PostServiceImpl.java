package cn.allams.hkjforum.service.impl;

import cn.allams.hkjforum.entity.Post;
import cn.allams.hkjforum.entity.ResultEnum;
import cn.allams.hkjforum.exception.MyException;
import cn.allams.hkjforum.repository.PostRepository;
import cn.allams.hkjforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 帖子用户逻辑层实现类
 * @author Allams
 */

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public void sendPost(Post post) throws MyException{
        postRepository.save(post);
    }

    @Override
    public void deletePost(Integer id) throws MyException {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAllPost(Integer curPage) throws MyException{
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"updateTime");
        Sort sort = Sort.by(order);
        Page<Post> page = postRepository.findAll(PageRequest.of(curPage-1,5,sort));
        if (curPage > page.getNumber() + 1 || curPage < 1) {
            throw new MyException(ResultEnum.PAGE_NOT_FOUND.getCode(),ResultEnum.PAGE_NOT_FOUND.getMsg());
        }
        return page;
    }

    @Override
    public Post findPostById(Integer id) throws MyException{
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setBrowseTimes(post.getBrowseTimes()+1);
            postRepository.save(post);
        }else {
            //没找到帖子，说明帖子不存在或者被删除了
            throw new MyException(ResultEnum.POST_NOT_FOUND.getCode(),ResultEnum.POST_NOT_FOUND.getMsg());
        }
        return post;
    }



}
