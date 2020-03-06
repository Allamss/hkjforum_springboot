package cn.allams.hkjforum.service.impl;

import cn.allams.hkjforum.entity.ResultEnum;
import cn.allams.hkjforum.entity.User;
import cn.allams.hkjforum.exception.MyException;
import cn.allams.hkjforum.repository.UserRepository;
import cn.allams.hkjforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑层实现类
 * @author Allams
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User login(User form) throws MyException{
        User user = userRepository.findUserByAccountIs(form.getAccount());
        if(user == null){
            throw new MyException(ResultEnum.USER_NOT_FROUND_ERROR.getCode(), ResultEnum.USER_NOT_FROUND_ERROR.getMsg());
        }
        if (!form.getPassword().equals(user.getPassword())){
            throw new MyException(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMsg());
        }
        return user;
    }

    @Override
    public void regist(User form) {

    }

    @Override
    public User findUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public void updateUser(Integer id, String username) {
        userRepository.updateUser(id, username);
    }
}
