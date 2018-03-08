package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.model.person.Person
import com.github.joostvdg.appregister.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
class PersonController(val personService: PersonService) {

    @GetMapping("/person")
    fun getAllPersons() : List<Person> {
        return personService.getAllPersons()
    }

    @GetMapping("/person/{personId}")
    fun getPerson(@PathVariable("personId") personId: String) : Person {
        return personService.getPerson(personId)
    }

    @PostMapping("/person")
    fun addPerson(@RequestBody person: Person) {
        personService.addPerson(person)
    }

    @PostMapping("/person/{personId}/teams/{teamId}")
    fun joinTeam(@PathVariable("personId") personId: String,
                 @PathVariable("teamId") teamId: String) {
        personService.joinTeam(personId, teamId)
    }
}