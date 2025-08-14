package com.hobeen.kovertest.post

import com.hobeen.kovertest.common.ApiResponse
import jakarta.servlet.http.HttpSession
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService
) {
    // 목록/상세 조회는 비로그인 허용
    @GetMapping
    fun list(@RequestParam(defaultValue = "0") page: Int,
             @RequestParam(defaultValue = "10") size: Int): ResponseEntity<ApiResponse<Page<DetailResponse>>> =
        ResponseEntity.ok(ApiResponse(data = postService.list(page, size)))

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) =
        ResponseEntity.ok(ApiResponse(data = postService.get(id)))

    // 생성/수정/삭제는 인터셉터로 보호됨
    @PostMapping
    fun create(@Valid @RequestBody req: CreateRequest, session: HttpSession) =
        ResponseEntity.ok(ApiResponse(data = postService.create(req, session)))

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody req: UpdateRequest, session: HttpSession) =
        ResponseEntity.ok(ApiResponse(data = postService.update(id, req, session)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, session: HttpSession) =
        ResponseEntity.ok(ApiResponse<Unit>(message = "삭제되었습니다").also { postService.delete(id, session) })
}
