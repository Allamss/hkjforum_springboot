package cn.allams.hkjforum.repository;

import cn.allams.hkjforum.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Allams
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
    /**
     * 通过帖子id查找回复
     * @param postId 帖子id
     * @return 回复集合
     */
    @Query(value = "SELECT * FROM reply r WHERE r.post_id=:postId", nativeQuery = true)
    List<Reply> findReplyByPostId(@Param("postId") Integer postId);
}
