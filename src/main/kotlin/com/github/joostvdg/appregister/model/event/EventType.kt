package com.github.joostvdg.appregister.model.event

import org.springframework.data.annotation.TypeAlias

@TypeAlias("eventType")
enum class EventType {
    CREATE, UPDATE, DELETE, FAILURE, OTHER
}