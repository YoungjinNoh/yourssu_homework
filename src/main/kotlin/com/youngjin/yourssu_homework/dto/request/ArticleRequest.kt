package com.youngjin.yourssu_homework.dto.request

data class ArticleRequest(
        //id가 null이면 신규, 아니면 수정으로 하면될듯
        //val id: Long?,
        //id는 패스에서 바로 서비스 인자로 넘길거니까 dto에는 필요없을듯?
        val email: String,
        val password: String,
        val title: String?=null,
        val content: String?=null,
)