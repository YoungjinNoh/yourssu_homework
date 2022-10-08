package com.youngjin.yourssu_homework.repository

import com.youngjin.yourssu_homework.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}