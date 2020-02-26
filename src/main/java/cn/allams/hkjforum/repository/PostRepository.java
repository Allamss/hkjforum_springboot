package cn.allams.hkjforum.repository;

import cn.allams.hkjforum.entity.Post;
import org.hibernate.annotations.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Allams
 */
public interface PostRepository extends JpaRepository<Post,Integer> {
}
