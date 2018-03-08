package com.github.joostvdg.appregister.service

import com.github.joostvdg.appregister.model.person.Person
import com.github.joostvdg.appregister.model.team.Team
import com.github.joostvdg.appregister.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


interface TeamService {
    fun addTeam(team: Team)
    fun getAllTeams(): List<Team>
    fun getTeam(identifier: String): Team
    fun getTeamMembers(identifier: String): List<Person>
}

@Service
class TeamServiceImpl: TeamService {

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var personService: PersonService

    override fun getTeam(identifier: String): Team {
        return teamRepository.findById(identifier).get()
    }

    override fun addTeam(team: Team) {
        var teamToSave = team
        if (team.identifier.isNullOrBlank()) {
            teamToSave = team.copy(identifier = UUID.randomUUID().toString())
        }
        teamRepository.save(teamToSave)
    }

    override fun getAllTeams(): List<Team> {

        return teamRepository.findAll().toList()
    }

    override fun getTeamMembers(identifier: String): List<Person> {
        // TODO: create custom query
        val team = getTeam(identifier)
        return personService.getAllPersons().filter {  person -> person.teams.contains(team) }
    }
}