package com.hs.blog.config;

import com.hs.blog.interceptor.ForeInterceptor;
import com.hs.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO: 配置拦截器
 *
 * @author 83998
 * @date 2019/3/4 21:44
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerInterceptor getForeInterceptor() {
        return new ForeInterceptor();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login", "/in");
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/admin");
        registry.addInterceptor(getForeInterceptor()).addPathPatterns("/**").excludePathPatterns("/in", "/admin/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/in").setViewName("admin/login");
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // static/** : 添加静态资源访问路径为static文件夹及子文件夹下的文件
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
