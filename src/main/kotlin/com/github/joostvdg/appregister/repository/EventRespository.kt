package com.github.joostvdg.appregister.repository

import com.github.joostvdg.appregister.model.event.Event
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository

interface EventRespository: ElasticsearchCrudRepository<Event, String> {
    fun findByTargetIdentifier(targetIdentifier: String): List<Event>
}
