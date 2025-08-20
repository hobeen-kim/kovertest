package com.hobeen.kovertest.user

import com.hobeen.kovertest.auth.SESSION_USER_ID
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpSession
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {
    private val encoder = BCryptPasswordEncoder()

    @Transactional
    fun signup(req: SignupRequest): MeResponse {
        if (userRepository.existsByUsername(req.username)) {
            throw IllegalArgumentException("이미 사용 중인 아이디입니다.")
        }
        val user = User(
            username = req.username,
            passwordHash = encoder.encode(req.password),
            displayName = req.displayName,
            email = req.email
        )
        val saved = userRepository.save(user)
        return MeResponse(saved.id, saved.username, saved.displayName, saved.email)
    }

    fun login(req: LoginRequest, session: HttpSession): MeResponse {
        val user = userRepository.findByUsername(req.username)
            .orElseThrow { IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.") }
        if (!encoder.matches(req.password, user.passwordHash)) {
            throw IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.")
        }
        session.setAttribute(SESSION_USER_ID, user.id)
        return MeResponse(user.id, user.username, user.displayName, user.email)
    }

    fun me(session: HttpSession): MeResponse {
        val id = session.getAttribute(SESSION_USER_ID) as Long?
            ?: throw SecurityException("로그인이 필요합니다.")
        val user = userRepository.findById(id)
            .orElseThrow { EntityNotFoundException("사용자를 찾을 수 없습니다.") }
        return MeResponse(user.id, user.username, user.displayName, user.email)
    }

    @Transactional
    fun updateMe(req: UpdateRequest, session: HttpSession): MeResponse {
        val id = session.getAttribute(SESSION_USER_ID) as Long?
            ?: throw SecurityException("로그인이 필요합니다.")
        val user = userRepository.findById(id)
            .orElseThrow { EntityNotFoundException("사용자를 찾을 수 없습니다.") }
        var changed = false
        req.displayName?.let { user.displayName = it; changed = true }
        if (req.email != null) { user.email = req.email; changed = true }
        req.password?.let { user.passwordHash = encoder.encode(it); changed = true }
        if (changed) user.updatedAt = LocalDateTime.now()
        return MeResponse(user.id, user.username, user.displayName, user.email)
    }

    @Transactional
    fun deleteMe(session: HttpSession) {
        val id = session.getAttribute(SESSION_USER_ID) as Long?
            ?: throw SecurityException("로그인이 필요합니다.")
        userRepository.deleteById(id)
        session.invalidate()
    }

    fun test(a: String): String {
        return "hello $a!!"
    }
}
