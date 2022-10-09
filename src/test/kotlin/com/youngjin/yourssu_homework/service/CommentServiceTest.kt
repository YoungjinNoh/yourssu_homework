package com.youngjin.yourssu_homework.service

import com.youngjin.yourssu_homework.dto.request.ArticleRequest
import com.youngjin.yourssu_homework.dto.request.CommentRequest
import com.youngjin.yourssu_homework.dto.request.UserRequest
import com.youngjin.yourssu_homework.repository.ArticleRepository
import com.youngjin.yourssu_homework.repository.CommentRepository
import com.youngjin.yourssu_homework.repository.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CommentServiceTest @Autowired constructor(
        private val commentService: CommentService,
        private val articleService: ArticleService,
        private val userService: UserService,
        private val commentRepository: CommentRepository,
        private val articleRepository: ArticleRepository,
        private val userRepository: UserRepository,
){
    @AfterEach
    fun clean() {
        commentRepository.deleteAll()
        articleRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun createCommentTest(){
        val userRequest= UserRequest("email@urssu.com","password","username")
        val articleRequest= ArticleRequest("email@urssu.com","password","title","content")
        val commentRequest=CommentRequest("email@urssu.com","password","content")

        userService.create(userRequest)
        val articleId=articleService.create(articleRequest).articleId
        commentService.create(articleId,commentRequest)

        val comments=commentRepository.findAll()
        assertThat(comments).hasSize(1)
        assertThat(comments[0].content).isEqualTo(commentRequest.content)
    }

    @Test
    fun updateCommentTest(){
        val userRequest= UserRequest("email@urssu.com","password","username")
        val articleRequest= ArticleRequest("email@urssu.com","password","title","content")
        val commentRequest=CommentRequest("email@urssu.com","password","content")
        val updatedCommentRequest=CommentRequest("email@urssu.com","password","content2")

        userService.create(userRequest)
        val articleId=articleService.create(articleRequest).articleId
        val commentId=commentService.create(articleId,commentRequest).commentId
        commentService.update(commentId,updatedCommentRequest)

        val comments=commentRepository.findAll()
        assertThat(comments).hasSize(1)
        assertThat(comments[0].content).isEqualTo(updatedCommentRequest.content)
    }

    @Test
    fun deleteCommentTest(){
        val userRequest= UserRequest("email@urssu.com","password","username")
        val articleRequest= ArticleRequest("email@urssu.com","password","title","content")
        val commentRequest=CommentRequest("email@urssu.com","password","content")
        val deletedCommentRequest=CommentRequest("email@urssu.com","password")

        userService.create(userRequest)
        val articleId=articleService.create(articleRequest).articleId
        val commentId=commentService.create(articleId,commentRequest).commentId
        commentService.delete(commentId,deletedCommentRequest)

        val comments=commentRepository.findAll()
        assertThat(comments).hasSize(0)
    }
}