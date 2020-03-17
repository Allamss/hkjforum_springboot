package cn.allams.hkjforum.mapper;
import cn.allams.hkjforum.entity.User;

//@Mapper
public interface UserMapper {

    //@Select("SELECT * FROM forum_user WHERE account=#{account}")
    public User findStudentByAccount(int account)throws Exception;
    //@Select("SELECT * FROM forum_user WHERE id=#{id}")
    public User findUserById(Integer id);
}
