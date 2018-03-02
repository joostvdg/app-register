package com.github.joostvdg.appregister.model

import org.springframework.data.annotation.TypeAlias

@TypeAlias("attribute")
class Attribute(val key: String, var value: String)