package com.lwh.config;


import com.lwh.filter.SessionExpireFilter;
import com.lwh.interceptor.AuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {

    @Autowired
    AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截器
        //excludePathPattern 用户排除拦截
        //映射为user的控制器下的所有映射
        //registry.addInterceptor(authorityInterceptor).addPathPatterns("/userlogin").excludePathPatterns("/index","/")
        registry.addInterceptor(authorityInterceptor);
        super.addInterceptors(registry);
    }

    @Bean("myFilter")
    public Filter uploadFilter(){
        return new SessionExpireFilter();
    }

    @Bean
    public FilterRegistrationBean uploadFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("myFilter"));
        registration.addUrlPatterns("/**");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
}
