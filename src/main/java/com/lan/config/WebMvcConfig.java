package com.lan.config;

import com.lan.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AuthorizationProperties authorizationProperties;

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){

        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**").excludePathPatterns(authorizationProperties.getIgnoreUrls());
    }
    /*
     * @注释:配置允许跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("*").allowedHeaders("*").allowedMethods("*").maxAge(3600);
    }
}
