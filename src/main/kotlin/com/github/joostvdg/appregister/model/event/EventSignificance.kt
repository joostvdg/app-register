package com.github.joostvdg.appregister.model.event

import org.springframework.data.annotation.TypeAlias

@TypeAlias("eventSignificance")
enum class EventSignificance {
    VERY_HIGH, HIGH, NEUTRAL,LOW, VERY_LOW
}