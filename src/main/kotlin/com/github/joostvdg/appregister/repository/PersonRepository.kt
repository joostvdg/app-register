package com.github.joostvdg.appregister.repository

import com.github.joostvdg.appregister.model.person.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, String>