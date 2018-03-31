package com.github.joostvdg.appregister

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
open class AppRegisterApplication

fun main(args: Array<String>) {
    runApplication<AppRegisterApplication>(*args)

}
