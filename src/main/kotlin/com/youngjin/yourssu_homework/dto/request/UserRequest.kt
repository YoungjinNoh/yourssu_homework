package com.youngjin.yourssu_homework.dto.request

data class UserRequest(
        //이메일도 기본키느낌으로 설계해야될듯??? 이메일이 아이디인셈이니까
        //그니까 나중에 findBy머시기로 찾아도 바로찾을수 있는 속성이 request 에 하나들어있어야됨
        val email: String,
        val password: String,
        val username: String,
)