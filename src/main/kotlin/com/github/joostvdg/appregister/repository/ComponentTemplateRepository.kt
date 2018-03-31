package com.github.joostvdg.appregister.repository

import com.github.joostvdg.appregister.model.template.ComponentTemplate
import org.springframework.data.mongodb.repository.MongoRepository

interface ComponentTemplateRepository: MongoRepository<ComponentTemplate, String>
