package cn.allams.hkjforum.service;

import cn.allams.hkjforum.entity.Post;
import org.springframework.data.domain.Page;

/**
 * 帖子服务层接口
 * @author Allams
 */
public interface PostService {
    /**
     * 发帖方法
     * @param post 需要发送的帖子
     */
    void sendPost(Post post);

    /**
     * 删除帖子方法
     * @param id 删除帖子的id
     */
    void deletePost(Integer id);

    /**
     * 分页显示所有帖子
     * @param curPage 第几页
     * @return
     */
    Page<Post> findAllPost(Integer curPage);

    /**
     * 根据id找帖子
     * @param id 帖子id
     * @return 帖子
     */
    Post findPostById(Integer id);


}
