package com.youngjin.yourssu_homework.controller

import com.youngjin.yourssu_homework.dto.request.UserRequest
import com.youngjin.yourssu_homework.dto.response.UserResponse
import com.youngjin.yourssu_homework.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        private val userService: UserService
) {
    @PostMapping("/user/create")
    fun createUser(@RequestBody request: UserRequest) :UserResponse{
        return userService.create(request)
    }

    @PostMapping("/user/delete")
    fun deleteUser(@RequestBody request: UserRequest){
        userService.delete(request)
    }
}