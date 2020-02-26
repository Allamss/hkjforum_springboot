package cn.allams.hkjforum.mapper;
import cn.allams.hkjforum.entity.User;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {

    @Select("SELECT * FROM forum_user WHERE account=#{account}")
    public User findStudentByAccount(int account)throws Exception;
    @Select("SELECT * FROM forum_user WHERE id=#{id}")
    public User findUserById(Integer id);

    public void addUser(User user)throws Exception;

    public String findUsernameById(int id)throws Exception;

    public void updateUsername(User user)throws Exception;
}
