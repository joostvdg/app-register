package com.github.joostvdg.appregister.service

import com.github.joostvdg.appregister.model.template.ComponentTemplate
import com.github.joostvdg.appregister.model.template.TemplateAttribute
import com.github.joostvdg.appregister.repository.ComponentTemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface TemplateService {
    fun getAllComponentTemplate(): List<ComponentTemplate>
    fun addComponentTemplate(componentTemplate: ComponentTemplate)
    fun setIdentifierIfAbsent(attribute: TemplateAttribute)
}

@Service
class TemplateServiceImpl: TemplateService {

    @Autowired
    lateinit var componentTemplateRepository: ComponentTemplateRepository

    override fun getAllComponentTemplate(): List<ComponentTemplate> {
        return componentTemplateRepository.findAll().toList()
    }

    override fun addComponentTemplate(componentTemplate: ComponentTemplate) {
        var templateToSave = componentTemplate
        if (templateToSave.identifier.isNullOrBlank()) {
            templateToSave = componentTemplate.copy(identifier = UUID.randomUUID().toString())
        }
        for (attribute in templateToSave.attributes){
            setIdentifierIfAbsent(attribute)
        }
        componentTemplateRepository.save(templateToSave)
    }

    override fun setIdentifierIfAbsent(attribute: TemplateAttribute) {
        if(attribute.identifier.isNullOrBlank()) {
            attribute.identifier = UUID.randomUUID().toString()
        }
    }

}
