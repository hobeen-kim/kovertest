package com.hobeen.kovertest.post

import com.hobeen.kovertest.auth.SESSION_USER_ID
import com.hobeen.kovertest.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpSession
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    private fun currentUserId(session: HttpSession): Long =
        session.getAttribute(SESSION_USER_ID) as Long?
            ?: throw SecurityException("로그인이 필요합니다.")

    @Transactional
    fun create(req: CreateRequest, session: HttpSession): DetailResponse {
        val user = userRepository.findById(currentUserId(session))
            .orElseThrow { EntityNotFoundException("사용자를 찾을 수 없습니다.") }
        val post = Post(title = req.title, content = req.content, author = user)
        val saved = postRepository.save(post)
        return DetailResponse(saved.id, saved.title, saved.content, saved.author.id, saved.author.displayName)
    }

    @Transactional
    fun update(postId: Long, req: UpdateRequest, session: HttpSession): DetailResponse {
        val uid = currentUserId(session)
        val post = postRepository.findById(postId)
            .orElseThrow { EntityNotFoundException("게시글을 찾을 수 없습니다.") }
        if (post.author.id != uid) throw SecurityException("본인이 작성한 글만 수정할 수 있습니다.")
        req.title?.let { post.title = it }
        req.content?.let { post.content = it }
        post.updatedAt = LocalDateTime.now()
        return DetailResponse(post.id, post.title, post.content, post.author.id, post.author.displayName)
    }

    @Transactional
    fun delete(postId: Long, session: HttpSession) {
        val uid = currentUserId(session)
        val post = postRepository.findById(postId)
            .orElseThrow { EntityNotFoundException("게시글을 찾을 수 없습니다.") }
        if (post.author.id != uid) throw SecurityException("본인이 작성한 글만 삭제할 수 있습니다.")
        postRepository.delete(post)
    }

    fun get(postId: Long): DetailResponse {
        val post = postRepository.findById(postId)
            .orElseThrow { EntityNotFoundException("게시글을 찾을 수 없습니다.") }
        return DetailResponse(post.id, post.title, post.content, post.author.id, post.author.displayName)
    }

    fun list(page: Int, size: Int): Page<DetailResponse> {
        val pageable: Pageable = PageRequest.of(page.coerceAtLeast(0), size.coerceIn(1, 100))
        return postRepository.findAllByOrderByIdDesc(pageable)
            .map { DetailResponse(it.id, it.title, it.content, it.author.id, it.author.displayName) }
    }
}
