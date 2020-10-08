package com.timhuo.passbook.merchants.security;

import com.timhuo.passbook.merchants.constant.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 权限拦截器
 * @author: Tim_Huo
 * @created: 2020/10/08 06:54
 */
@Configuration
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(Constants.TOKEN_STRING);
        if (StringUtils.isEmpty(token)) {
            throw new Exception("Header 中缺少" + Constants.TOKEN_STRING + "!");
        }

        if (!token.equals(Constants.TOKEN)) {
            throw new Exception("Header 中" + Constants.TOKEN_STRING + "错误！");
        }

        AccessContext.setToken(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除token
        AccessContext.clearAccessKey();
    }
}
