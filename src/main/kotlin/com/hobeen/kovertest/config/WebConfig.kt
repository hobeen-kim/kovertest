package com.hobeen.kovertest.config

import com.hobeen.kovertest.auth.SessionAuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val sessionAuthInterceptor: SessionAuthInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(sessionAuthInterceptor)
            .addPathPatterns(
                "/api/users/me", // GET, PATCH, DELETE
                "/api/posts/**" // 글 작성/수정/삭제 보호, 목록/상세는 아래 exclude
            )
            .excludePathPatterns(
                "/api/users/signup",
                "/api/users/login",
                "/api/posts",
                "/api/posts/*"
            )
    }
}
