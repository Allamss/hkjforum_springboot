package cn.allams.hkjforum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@MapperScan(value = "cn.allams.hkjforum.mapper")
@EnableJpaAuditing
@SpringBootApplication
public class HkjforumApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkjforumApplication.class, args);
    }

}
