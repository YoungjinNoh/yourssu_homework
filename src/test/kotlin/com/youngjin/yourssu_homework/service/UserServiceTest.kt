package com.youngjin.yourssu_homework.service

import com.youngjin.yourssu_homework.dto.request.UserRequest
import com.youngjin.yourssu_homework.repository.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
        private val userService: UserService,
        private val userRepository: UserRepository,
){
    @AfterEach
    fun clean(){
        userRepository.deleteAll()
    }

    @Test
    fun createUserTest(){
        val userRequest= UserRequest("email@urssu.com","password","username")

        userService.create(userRequest)

        val users=userRepository.findAll()
        assertThat(users).hasSize(1)
        assertThat(users[0].email).isEqualTo(userRequest.email)
        assertThat(users[0].password).isEqualTo(userRequest.password)
        assertThat(users[0].username).isEqualTo(userRequest.username)
    }

    @Test
    fun deleteUserTest(){
        val userRequest= UserRequest("email@urssu.com","password","username")
        val deletedUserRequest= UserRequest("email@urssu.com","password")

        userService.create(userRequest)
        userService.delete(deletedUserRequest)

        val users=userRepository.findAll()
        assertThat(users).hasSize(0)
    }
}