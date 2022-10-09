package com.youngjin.yourssu_homework.dto.response

import com.youngjin.yourssu_homework.entity.Comment
import com.youngjin.yourssu_homework.entity.User

data class CommentResponse(
        val commentId: Long,
        val email: String,
        val content: String,
){
    companion object{
        fun of(comment: Comment,user: User):CommentResponse{
            return CommentResponse(comment.id!!,user.email,comment.content)
        }
    }
}