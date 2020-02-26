package cn.allams.hkjforum.repository;

import cn.allams.hkjforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA
 * @author Allams
 */

public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 通过账号查询用户
     * @param account 用户名
     * @return 查找到的用户
     */
    @Query(value = "SELECT * FROM user u WHERE u.account = :account",nativeQuery = true)
    User findUserByAccountIs(@Param("account") Integer account);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.username = ?2 WHERE u.id=?1")
    void updateUser(Integer id, String username);
}
