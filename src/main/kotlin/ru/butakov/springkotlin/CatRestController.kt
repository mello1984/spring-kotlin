package ru.butakov.springkotlin

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class CatRestController(val repository: CatRepository) {

    @GetMapping("/cats")
    fun findAll(): List<Cat> = repository.findAll()

    @GetMapping("/cats/by-name/{name}")
    fun findByName(@PathVariable name: String): List<Cat> = repository.findByName(name)

    @GetMapping("/cats/{id}")
    fun findById(@PathVariable id: Long): Cat = repository.findById(id).orElseThrow() { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping("/cats")
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody cat: Cat): Cat = repository.save(cat)


}