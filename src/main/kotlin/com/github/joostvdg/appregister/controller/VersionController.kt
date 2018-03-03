package com.github.joostvdg.appregister.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VersionController {

    @GetMapping("/version")
    fun helloKotlin(): version {
      return version("0.1.0") // TODO: get version from gradle config
    }
}

data class version (val version: String)