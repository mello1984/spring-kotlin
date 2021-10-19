package ru.butakov.springkotlin

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
class CatController(val repository: CatRepository) {

    @GetMapping("/")
    fun index(
        model: Model,
        @RequestParam("cats_name", required = false, defaultValue = "") name: String,
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "3") size: Int,
        ): String {
        val p = if (name == "") getAllByPage(page, size) else getByNameByPage(name, page, size)
        model.addAttribute("key", if (name == "") "" else name)
        model.addAttribute("cats", p)
        model.addAttribute("pag", p)
        model.addAttribute("pageNumber", p.pageable.pageNumber)
        model.addAttribute("totalPages", p.totalPages)
        return "index"
    }

    @GetMapping("/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val byId = repository.findById(id).orElseThrow() { ResponseStatusException(HttpStatus.NOT_FOUND) }
        model.addAttribute("cat", byId)
        return "edit"
    }

    fun getAllByPage(page: Int, size: Int): Page<Cat> {
        val request: PageRequest = PageRequest.of(page, size)
        return repository.findAll(request)
    }

    fun getByNameByPage(name: String, page: Int, size: Int): Page<Cat> {
        val request: PageRequest = PageRequest.of(page, size)
        return repository.findByName(name, request)
    }
}