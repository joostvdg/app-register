package com.github.joostvdg.appregister.model.template

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "templateAttributes")
@TypeAlias("templateAttribute")
data class TemplateAttribute (@Id var identifier: String?,
                              val key: String,
                              val description: String,
                              var defaultValue: String,
                              var valueType: String,
                              val required: Boolean,
                              val validate: Boolean)
