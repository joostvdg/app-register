package com.github.joostvdg.appregister.service

import com.github.joostvdg.appregister.model.event.Event
import com.github.joostvdg.appregister.repository.EventRespository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

interface EventService {
    fun getEventsForResource(resourceIdentifier: String): List<Event>
    fun addEvent(event: Event)
}

@Service
class EventServiceImpl: EventService {

    @Autowired
    lateinit var eventRespository: EventRespository

    override fun addEvent(event: Event) {
        if (event.identifier.isNullOrBlank()) {
            event.identifier = UUID.randomUUID().toString()
        }
        event.recorded = LocalDateTime.now()
        eventRespository.save(event)
    }

    override fun getEventsForResource(resourceIdentifier: String): List<Event> {
        return eventRespository.findByTargetIdentifier(resourceIdentifier)
    }

}
