package com.github.joostvdg.appregister.service

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun getHello(): String {
        return "HelloWorld with Kotlin!"
    }
}