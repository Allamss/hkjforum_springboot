package cn.allams.hkjforum.service;

import cn.allams.hkjforum.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑层接口
 * @author Allams
 */

public interface UserService {
    /**
     *
     * @param form 用户登录填写表单
     * @return 用户信息
     */
    User login(User form);

    /**
     *
     * @param form 注册用户填写信息
     */
    void regist(User form);

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    User findUserById(Integer id);

    /**
     *
     * @param id 更改信息的用户id
     * @param username 更改的用户名
     */
    void updateUser(Integer id, String username);
}
