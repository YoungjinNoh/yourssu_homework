package com.youngjin.yourssu_homework.dto.response

import com.youngjin.yourssu_homework.entity.User

data class UserResponse(
        val email: String,
        val username: String?,
) {
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(user.email, user.username)
        }
    }
}