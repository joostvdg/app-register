package com.github.joostvdg.appregister.service

import com.github.joostvdg.appregister.model.Attribute
import com.github.joostvdg.appregister.model.Component
import com.github.joostvdg.appregister.model.Product
import com.github.joostvdg.appregister.model.ResourceIdentifier
import org.springframework.stereotype.Service

@Service
class ProductService {

    fun getAllProducts(): List<Product> {
        val jenkinsRemotingIdentifier = ResourceIdentifier.fromMavenGav("org.jenkins-ci.main", "remoting", "3.17")
        val jenkinsRemotingComponent = Component("Kohsuke Kawaguchi", 2005, jenkinsRemotingIdentifier, listOf())

        val jenkinsResourceIdentifier = ResourceIdentifier.fromMavenGav("org.jenkins-ci.main", "jenkins-core", "2.109")
        val nameAttribute = Attribute("name", "jenkins")
        val mainLanguageAttribute = Attribute("main-language", "java")
        val jenkins = Product("Kohsuke Kawaguchi", 2010, jenkinsResourceIdentifier, listOf(jenkinsRemotingComponent), listOf(nameAttribute, mainLanguageAttribute))
        return listOf(jenkins)
    }
}