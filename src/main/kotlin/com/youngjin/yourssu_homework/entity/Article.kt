package com.youngjin.yourssu_homework.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Article(
        val created_at: LocalDateTime,

        var updated_at: LocalDateTime?,

        var content: String,

        var title: String,

        @ManyToOne
        val user_id: Long,

        @Id
        @Column(name = "article_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?=null,
) {
    init {
        if (title.isBlank() || content.isBlank()) {
            throw IllegalArgumentException("게시글의 재목과 내용은 비어 있을 수 없습니다.")
        }
    }

    fun update(title: String, content: String) {
        if (title.isBlank() || content.isBlank()) {
            throw IllegalArgumentException("게시글의 재목과 내용은 비어 있을 수 없습니다.")
        }
        this.title = title
        this.content = content
        this.updated_at= LocalDateTime.now()
    }
}