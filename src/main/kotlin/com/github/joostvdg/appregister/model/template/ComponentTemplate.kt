package com.github.joostvdg.appregister.model.template

import com.github.joostvdg.appregister.model.product.ComponentType
import org.springframework.data.annotation.Id

data class ComponentTemplate (@Id val identifier: String?,
                              val type: ComponentType,
                              var attributes: List<TemplateAttribute>)
