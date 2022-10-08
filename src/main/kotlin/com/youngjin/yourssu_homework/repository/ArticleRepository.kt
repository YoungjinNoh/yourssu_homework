package com.youngjin.yourssu_homework.repository

import com.youngjin.yourssu_homework.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
}