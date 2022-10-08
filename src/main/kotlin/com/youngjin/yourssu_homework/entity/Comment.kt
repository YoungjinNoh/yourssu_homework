package com.youngjin.yourssu_homework.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Comment(
        val created_at: LocalDateTime,

        var updated_at: LocalDateTime,

        var content: String,

        @ManyToOne
        val article_id: Int,

        @ManyToOne
        val user_id: Int,

        @Id
        @Column(name = "comment_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
) {
    init {
        if (content.isBlank()) {
            throw IllegalArgumentException("댓글의 내용은 비어 있을 수 없습니다.")
        }
    }

    fun update(content: String) {
        this.content = content
    }
}