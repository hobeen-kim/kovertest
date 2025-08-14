package com.hobeen.kovertest.user

import com.hobeen.kovertest.common.ApiResponse
import jakarta.servlet.http.HttpSession
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    init {
        userService.signup(
            SignupRequest(
                username = "admin",
                password = "password",
                displayName = "admin",
                email = "5l8Y9@example.com"
            )
        )
    }

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody req: SignupRequest) =
        ResponseEntity.ok(ApiResponse(data = userService.signup(req)))

    @PostMapping("/login")
    fun login(@Valid @RequestBody req: LoginRequest, session: HttpSession) =
        ResponseEntity.ok(ApiResponse(data = userService.login(req, session)))

    @GetMapping("/me")
    fun me(session: HttpSession) =
        ResponseEntity.ok(ApiResponse(data = userService.me(session)))

    @PatchMapping("/me")
    fun update(@Valid @RequestBody req: UpdateRequest, session: HttpSession) =
        ResponseEntity.ok(ApiResponse(data = userService.updateMe(req, session)))

    @DeleteMapping("/me")
    fun delete(session: HttpSession) =
        ResponseEntity.ok(ApiResponse<Unit>(message = "탈퇴되었습니다").also { userService.deleteMe(session) })
}
