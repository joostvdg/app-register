package com.github.joostvdg.appregister.repository

import com.github.joostvdg.appregister.model.team.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepository: CrudRepository<Team, String>