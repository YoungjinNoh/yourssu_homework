package com.youngjin.yourssu_homework.repository

import com.youngjin.yourssu_homework.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    //데이터가 없다해도 null을 반환하지 않음.
    fun findAllByArticleId(articleId: Long): List<Comment>
}