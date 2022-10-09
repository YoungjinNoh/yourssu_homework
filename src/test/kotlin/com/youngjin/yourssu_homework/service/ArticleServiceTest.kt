package com.youngjin.yourssu_homework.service

import com.youngjin.yourssu_homework.dto.request.ArticleRequest
import com.youngjin.yourssu_homework.dto.request.UserRequest
import com.youngjin.yourssu_homework.repository.ArticleRepository
import com.youngjin.yourssu_homework.repository.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArticleServiceTest @Autowired constructor(
        private val articleService: ArticleService,
        private val userService: UserService,
        private val articleRepository: ArticleRepository,
        private val userRepository: UserRepository,
) {
    @AfterEach
    fun clean() {
        articleRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun createArticleTest(){
        val userRequest=UserRequest("email@urssu.com","password","username")
        val articleRequest=ArticleRequest("email@urssu.com","password","title","content")

        userService.create(userRequest)
        articleService.create(articleRequest)

        val articles=articleRepository.findAll()
        assertThat(articles).hasSize(1)
        assertThat(articles[0].title).isEqualTo(articleRequest.title)
        assertThat(articles[0].content).isEqualTo(articleRequest.content)
    }

    @Test
    fun updateArticleTest(){
        val userRequest=UserRequest("email@urssu.com","password","username")
        val articleRequest=ArticleRequest("email@urssu.com","password","title","content")
        val updatedArticleRequest=ArticleRequest("email@urssu.com","password","title2","content2")

        userService.create(userRequest)
        val articleId=articleService.create(articleRequest).articleId
        articleService.update(articleId,updatedArticleRequest)

        val articles=articleRepository.findAll()
        assertThat(articles).hasSize(1)
        assertThat(articles[0].title).isEqualTo(updatedArticleRequest.title)
        assertThat(articles[0].content).isEqualTo(updatedArticleRequest.content)
    }

    @Test
    fun deleteArticleTest(){
        val userRequest=UserRequest("email@urssu.com","password","username")
        val articleRequest=ArticleRequest("email@urssu.com","password","title","content")
        val deletedArticleRequest=ArticleRequest("email@urssu.com","password")

        userService.create(userRequest)
        val articleId=articleService.create(articleRequest).articleId
        articleService.delete(articleId,deletedArticleRequest)

        val articles=articleRepository.findAll()
        assertThat(articles).hasSize(0)
    }
}