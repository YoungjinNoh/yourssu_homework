package com.youngjin.yourssu_homework.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class User(
        val created_at: LocalDateTime,

        val updated_at: LocalDateTime?,

        val email: String,

        val password: String,

        val username: String,

        @Id
        @Column(name = "user_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
) {
}