package ru.butakov.springkotlin

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class CatController(val repository: CatRepository) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("cats", repository.findAll())
        return "index"
    }

    @GetMapping("/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val byId = repository.findById(id).orElseThrow() { ResponseStatusException(HttpStatus.NOT_FOUND) }
        model.addAttribute("cat", byId)
        return "edit"
    }


}