package cn.allams.hkjforum.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author Allams
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "学号不能为空")
    @DecimalMin(value = "100000000", message = "学号太短")
    private Integer account;
    @NotBlank(message = "密码不能为空")
    @Length(min = 3, max = 16, message = "密码长度在3到16之间")
    private String password;
    @Length(min = 2, max = 10, message = "用户名长度在2到10之间")
    private String username;
    @Column(name = "if_super")
    private Byte ifSuper;
    @CreatedDate
    @Column(name = "gmt_create")
    private Date createTime;
    @Column(name = "gmt_modified")
    private Date modifiedTime;

    public Integer getId() {
        return id;
    }


    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getIfSuper() {
        return ifSuper;
    }

    public void setIfSuper(Byte ifSuper) {
        this.ifSuper = ifSuper;
    }
}
