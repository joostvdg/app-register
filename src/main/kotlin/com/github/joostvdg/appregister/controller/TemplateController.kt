package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.model.template.ComponentTemplate
import com.github.joostvdg.appregister.service.TemplateService
import org.springframework.web.bind.annotation.*

@RestController
class TemplateController(val templateService: TemplateService) {

    @GetMapping("/templates/component")
    fun getAllComponentTemplates(): List<ComponentTemplate> {
        return templateService.getAllComponentTemplate()
    }

    @PostMapping("/templates/component")
    fun addNewEvent(@RequestBody componentTemplate: ComponentTemplate) {
        templateService.addComponentTemplate(componentTemplate)
    }
}
