package com.youngjin.yourssu_homework.service

import com.youngjin.yourssu_homework.dto.request.UserRequest
import com.youngjin.yourssu_homework.dto.response.UserResponse
import com.youngjin.yourssu_homework.entity.User
import com.youngjin.yourssu_homework.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserService(
        private val userRepository: UserRepository
) {
    @Transactional
    fun create(request: UserRequest): UserResponse {
        if(userRepository.findByEmail(request.email)!=null){
            throw IllegalArgumentException("이미 가입한 회원입니다.")
        }

        val user = User(LocalDateTime.now(), null, request.email, request.password, request.username)
        userRepository.save(user)
        return UserResponse.of(user)
    }

    @Transactional
    fun delete(request: UserRequest) {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        userRepository.delete(user)
    }
}