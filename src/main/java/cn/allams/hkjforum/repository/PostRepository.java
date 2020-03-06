package cn.allams.hkjforum.repository;

import cn.allams.hkjforum.entity.Post;
import cn.allams.hkjforum.entity.Reply;
import org.hibernate.annotations.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Allams
 */
public interface PostRepository extends JpaRepository<Post,Integer> {
}
