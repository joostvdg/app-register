package com.github.joostvdg.appregister.model

import org.springframework.data.annotation.TypeAlias

@TypeAlias("componentType")
enum class ComponentType(val prefix: String) {
    DEBIAN("deb"),
    DOCKER("https"),
    GENERIC("file"),
    MAVEN("gav"),
    NPM("npm"),
    NUGET("nuget"),
    PYTHON("PIP"),
    RPM("RPM")
}