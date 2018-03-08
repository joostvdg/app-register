package com.github.joostvdg.appregister.model.person

import com.github.joostvdg.appregister.model.team.Team
import org.springframework.data.mongodb.core.mapping.DBRef

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "persons")
@TypeAlias("person")
data class Person(@Id val identifier: String?, val fullName: String, var email: String ) {

    @DBRef(lazy = false)
    val teams: MutableList<Team> = ArrayList()
}