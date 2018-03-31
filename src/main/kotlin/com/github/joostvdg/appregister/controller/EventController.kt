package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.model.event.Event
import com.github.joostvdg.appregister.service.EventService
import org.springframework.web.bind.annotation.*

@RestController
class EventController(val eventService: EventService) {

    @GetMapping("/events/{resourceId}")
    fun getEventsForResource(@PathVariable("resourceId") resourceId: String): List<Event> {
        return eventService.getEventsForResource(resourceId)
    }

    @PostMapping("/events")
    fun addNewEvent(@RequestBody event: Event) {
        eventService.addEvent(event)
    }
}
