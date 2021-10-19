package ru.butakov.springkotlin

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CatRepository : JpaRepository<Cat, Long> {
    fun findByName(name: String): List<Cat>
    fun findByName(name: String, page: Pageable) : Page<Cat>
}