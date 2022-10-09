package com.youngjin.yourssu_homework.controller

import com.youngjin.yourssu_homework.dto.request.UserRequest
import com.youngjin.yourssu_homework.dto.response.UserResponse
import com.youngjin.yourssu_homework.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        private val userService: UserService
) {
    @PostMapping("/user/create")
    fun createUser(@RequestBody request: UserRequest): UserResponse {
        return userService.create(request)
    }

    @PostMapping("/user/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@RequestBody request: UserRequest) {
        userService.delete(request)
    }
}