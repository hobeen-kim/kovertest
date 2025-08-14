package com.hobeen.kovertest.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

const val SESSION_USER_ID = "USER_ID"

@Component
class SessionAuthInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 이미 로그인/회원가입/조회 전용은 WebConfig에서 제외 처리
        val userId = request.session.getAttribute(SESSION_USER_ID) as Long?
        if (userId == null) {
            response.status = 401
            response.contentType = "application/json"
            response.writer.write("{\"success\":false,\"message\":\"로그인이 필요합니다\"}")
            return false
        }
        return true
    }
}
