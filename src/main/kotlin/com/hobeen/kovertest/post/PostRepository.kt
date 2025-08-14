package com.hobeen.kovertest.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByOrderByIdDesc(pageable: Pageable): Page<Post>
}
