package com.youngjin.yourssu_homework.service

import com.youngjin.yourssu_homework.dto.request.CommentRequest
import com.youngjin.yourssu_homework.dto.response.CommentResponse
import com.youngjin.yourssu_homework.entity.Comment
import com.youngjin.yourssu_homework.repository.CommentRepository
import com.youngjin.yourssu_homework.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommentService(
        private val commentRepository: CommentRepository,
        private val userRepository: UserRepository,
) {
    @Transactional
    fun create(article_id: Long, request: CommentRequest): CommentResponse {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        val comment = Comment(LocalDateTime.now(),null,request.content!!,article_id,user.id!!)

        return CommentResponse.of(comment,user)
    }

    @Transactional
    fun update(id: Long, request: CommentRequest): CommentResponse {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        val comment=commentRepository.findById(id).orElse(null)
                ?:throw IllegalArgumentException("요청한 정보를 가진 댓글이 없습니다.")

        if(comment.user_id!=user.id){
            throw IllegalArgumentException("해당 댓글의 삭제 권한이 없는 회원입니다.")
        }
        comment.update(request.content!!)

        return CommentResponse.of(comment,user)
    }

    @Transactional
    fun delete(id: Long, request: CommentRequest) {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        val comment = commentRepository.findById(id).orElse(null)
                ?: throw IllegalArgumentException("요청한 정보를 가진 댓글이 없습니다.")

        if (comment.user_id != user.id) {
            throw IllegalArgumentException("해당 댓글의 삭제 권한이 없는 회원입니다.")
        }
        commentRepository.deleteById(id)
    }
}