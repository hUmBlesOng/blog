package com.hs.blog.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * TODO: 管理员类
 *
 * @author 83998
 * @date 2019/3/4 20:38
 */

@PropertySource(value = {"classpath:user/user.properties"})
@Component
@ConfigurationProperties(prefix = "user") // prefix = "user"：配置文件中哪个下面的所有属性进行一一映射
public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
