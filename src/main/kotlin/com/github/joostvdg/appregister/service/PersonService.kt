package com.github.joostvdg.appregister.service

import com.github.joostvdg.appregister.model.person.Person
import com.github.joostvdg.appregister.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface PersonService {
    fun getAllPersons(): List<Person>
    fun addPerson(person: Person)
    fun joinTeam(personId: String, teamId: String)
    fun getPerson(personId: String): Person
}

@Service
class PersonServiceImpl: PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var teamService: TeamService

    override fun getPerson(personId: String): Person {
        return personRepository.findById(personId).get()
    }

    override fun getAllPersons(): List<Person> {
        return personRepository.findAll().toList()
    }

    override fun addPerson(person: Person) {
        var personToSave = person
        if (person.identifier.isNullOrBlank()) {
            personToSave = person.copy(identifier = UUID.randomUUID().toString())
        }
        personRepository.save(personToSave)
    }

    override fun joinTeam(personId: String, teamId: String) {
        val existingTeam = teamService.getTeam(teamId)
        val existingPerson = personRepository.findById(personId)
        existingPerson.ifPresent {
            person ->
            person.teams.add(existingTeam)
            personRepository.save(person)
        }
    }

}