package com.github.joostvdg.appregister.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "components")
@TypeAlias("component")
class Component(@Id val identifier: ResourceIdentifier, val author: String, val created: Int, var dependencies: List<ResourceIdentifier>? )