package com.youngjin.yourssu_homework.dto.response

import com.youngjin.yourssu_homework.entity.Article
import com.youngjin.yourssu_homework.entity.User

data class ArticleResponse(
        val articleId: Long,
        val email: String,
        val title: String,
        val content: String,
) {
    companion object {
        fun of(article: Article, user: User): ArticleResponse {
            return ArticleResponse(article.id!!, user.email, article.title, article.content)
        }
    }
}