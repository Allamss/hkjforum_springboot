package cn.allams.hkjforum.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置Druid连接池
 * @author Allams
 */
@Configuration
public class DruidConfig {

    /**
     * 数据连接池管理页面用户名
     */
    @Value("${druid.loginUsername}")
    String loginUsername;
    /**
     * 数据连接池管理页面密码
     */
    @Value("${druid.loginPassword}")
    String loginPassword;
    /**
     * 数据连接池管理界面映射url
     */
    @Value("${druid.urlMapping}")
    String urlMapping;

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 1、配置管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),urlMapping);
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername", loginUsername);
        initParams.put("loginPassword", loginPassword);
        //默认允许所有
        initParams.put("allow","");
        bean.setInitParameters(initParams);
        return bean;
    }
    /**
     * 2、配置一个监控的Filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;

    }
}
