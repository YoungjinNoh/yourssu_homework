package com.youngjin.yourssu_homework.controller

import com.youngjin.yourssu_homework.dto.request.ArticleRequest
import com.youngjin.yourssu_homework.dto.request.CommentRequest
import com.youngjin.yourssu_homework.dto.response.ArticleResponse
import com.youngjin.yourssu_homework.dto.response.CommentResponse
import com.youngjin.yourssu_homework.entity.Article
import com.youngjin.yourssu_homework.entity.Comment
import com.youngjin.yourssu_homework.service.ArticleService
import com.youngjin.yourssu_homework.service.CommentService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ArticleController(
        private val articleService: ArticleService,
        private val commentService: CommentService
) {
    @PostMapping("/article/create")
    fun createArticle(@RequestBody request: ArticleRequest): ArticleResponse {
        return articleService.create(request)
    }

    @PutMapping("/article/{id}/update")
    fun updateArticle(
            @PathVariable id: Long, @RequestBody request: ArticleRequest
    ): ArticleResponse {
        return articleService.update(id, request)
    }

    @PostMapping("/article/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteArticle(
            @PathVariable id: Long, @RequestBody request: ArticleRequest
    ){
        articleService.delete(id, request)
    }

    @PostMapping("/article/{article_id}/comment/create")
    fun createComment(
            @PathVariable article_id: Long, @RequestBody request: CommentRequest
    ): CommentResponse {
        return commentService.create(article_id, request)
    }

    @PutMapping("/article/{article_id}/comment/{id}/update")
    fun updateComment(
            @PathVariable id: Long, @RequestBody request: CommentRequest
    ): CommentResponse {
        return commentService.update(id, request)
    }

    @PostMapping("/article/{article_id}/comment/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteComment(
            @PathVariable id: Long, @RequestBody request: CommentRequest
    ){
        commentService.delete(id, request)
    }
}