package com.youngjin.yourssu_homework.dto.request

data class CommentRequest(
        //val id:Long,
        val email:String,
        val password:String,
        val content:String?=null,
)