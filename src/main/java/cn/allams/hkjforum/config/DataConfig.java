package cn.allams.hkjforum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * 读取数据库配置
 * @author Allams
 */
@Configuration
@PropertySource("classpath:db.properties")
public class DataConfig {
}
