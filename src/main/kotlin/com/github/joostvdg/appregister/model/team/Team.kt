package com.github.joostvdg.appregister.model.team

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "teams")
@TypeAlias("team")
data class Team(@Id val identifier: String?, val name: String, var description: String) {

}