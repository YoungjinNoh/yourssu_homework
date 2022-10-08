package com.youngjin.yourssu_homework.service

import com.youngjin.yourssu_homework.dto.request.ArticleRequest
import com.youngjin.yourssu_homework.dto.response.ArticleResponse
import com.youngjin.yourssu_homework.entity.Article
import com.youngjin.yourssu_homework.repository.ArticleRepository
import com.youngjin.yourssu_homework.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ArticleService(
        //생성자가 하나일 경우에는 autowired 생략가능
        private val articleRepository: ArticleRepository,
        private val userRepository: UserRepository,
) {
    @Transactional
    fun create(request: ArticleRequest): ArticleResponse {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        val article = Article(LocalDateTime.now(), null, request.content!!, request.title!!, user.id!!)

        if(request.title!!.isBlank()||request.content!!.isBlank()){
            throw IllegalArgumentException("게시글의 제목 또는 내용이 비어 있습니다.")
        }

        return ArticleResponse.of(articleRepository.save(article), user)
    }

    @Transactional
    fun update(id: Long, request: ArticleRequest): ArticleResponse {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        //findById는 값을 못찾더라도 null이 아닌 optional타입을 반환한다? 그러므로 orElse로 예외처리
        val article = articleRepository.findById(id).orElse(null)
                ?: throw IllegalArgumentException("요청한 정보를 가진 게시글이 없습니다.")

        if(article.user_id!=user.id){
            throw IllegalArgumentException("해당 게시글의 삭제 권한이 없는 회원입니다.")
        }
        article.update(request.title!!, request.content!!)

        return ArticleResponse.of(articleRepository.save(article), user)
    }

    @Transactional
    fun delete(id:Long,request: ArticleRequest){
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
                ?: throw IllegalArgumentException("요청한 정보를 가진 회원이 없습니다.")
        val article = articleRepository.findById(id).orElse(null)
                ?: throw IllegalArgumentException("요청한 정보를 가진 게시글이 없습니다.")

        if(article.user_id!=user.id){
            throw IllegalArgumentException("해당 게시글의 삭제 권한이 없는 회원입니다.")
        }
        articleRepository.deleteById(id)
    }
}