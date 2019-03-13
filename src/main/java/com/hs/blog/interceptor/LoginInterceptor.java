package com.hs.blog.interceptor;

import com.hs.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO: 登录拦截器, 只有在进入后台管理页面时才会拦截请求
 *
 * @author 83998
 * @date 2019/3/4 20:49
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private User user;

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if(null != loginUser && user.getUsername().equals(loginUser.getUsername()) && user.getPassword().equals(loginUser.getPassword())){
            return true;
        }else{
            request.getRequestDispatcher("/in").forward(request, response);
//        request.getRequestDispatcher("/admin/login.html").forward(request,response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
