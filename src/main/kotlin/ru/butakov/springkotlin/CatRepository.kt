package ru.butakov.springkotlin

import org.springframework.data.jpa.repository.JpaRepository

interface CatRepository : JpaRepository<Cat, Long> {
    fun findByName(name: String): List<Cat>
}