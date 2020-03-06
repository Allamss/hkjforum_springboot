package cn.allams.hkjforum.config;

import cn.allams.hkjforum.entity.Reply;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置
 * @author Allams
 */
@Configuration
public class RedisConfig {
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean("sendReplyKeyGenerator")
    public KeyGenerator sendReplyKeyGenerator() {
        return (target, method, params) -> {
            Reply reply = (Reply)params[0];
            return "postId4replys"+reply.getPostId();
        };
    }
}
