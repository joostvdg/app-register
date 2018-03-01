package com.github.joostvdg.appregister

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AppRegisterApplication

fun main(args: Array<String>) {
    runApplication<AppRegisterApplication>(*args)
}
