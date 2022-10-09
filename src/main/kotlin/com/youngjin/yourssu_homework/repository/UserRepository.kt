package com.youngjin.yourssu_homework.repository

import com.youngjin.yourssu_homework.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailAndPassword(email: String,password:String): User?
    fun findByEmail(email:String):User?
}