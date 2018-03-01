package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.service.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(val helloService: HelloService) {

    @GetMapping("/hello")
    fun helloKotlin(): String {
        return helloService.getHello()
    }
}