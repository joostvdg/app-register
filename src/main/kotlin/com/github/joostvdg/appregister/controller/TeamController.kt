package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.model.person.Person
import com.github.joostvdg.appregister.model.team.Team
import com.github.joostvdg.appregister.service.TeamService
import org.springframework.web.bind.annotation.*

@RestController
class TeamController(val teamService: TeamService) {

    @GetMapping("/team")
    fun getAllTeams() : List<Team> {
        return teamService.getAllTeams()
    }

    @GetMapping("/team/{teamId}")
    fun getTeam(@PathVariable("teamId") teamId: String) : Team {
        return teamService.getTeam(teamId)
    }

    @GetMapping("/team/{teamId}/members")
    fun getMembersOfTeam(@PathVariable("teamId") teamId: String) : List<Person> {
        return teamService.getTeamMembers(teamId)
    }

    @PostMapping("/team")
    fun addTeam(@RequestBody team: Team) {
        teamService.addTeam(team)
    }
}