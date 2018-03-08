package com.github.joostvdg.appregister.service

import com.github.joostvdg.appregister.model.Attribute
import com.github.joostvdg.appregister.model.product.Component
import com.github.joostvdg.appregister.model.product.Product
import com.github.joostvdg.appregister.model.ResourceIdentifier
import com.github.joostvdg.appregister.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface ProductService {
    fun getAllProducts(): List<Product>
    fun getDummyProduct(): List<Product>
    fun addProduct(product: Product)
}

@Service
class ProductServiceImpl: ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    override fun getDummyProduct(): List<Product> {
        val jenkinsRemotingIdentifier = ResourceIdentifier.fromMavenGav("org.jenkins-ci.main", "remoting", "3.17")
        val jenkinsRemotingComponent = Component(jenkinsRemotingIdentifier, "Kohsuke Kawaguchi", 2005, listOf())

        val jenkinsResourceIdentifier = ResourceIdentifier.fromMavenGav("org.jenkins-ci.main", "jenkins-core", "2.109")
        val nameAttribute = Attribute("name", "jenkins")
        val mainLanguageAttribute = Attribute("main-language", "java")
        val identifier = UUID.randomUUID().toString()
        val jenkins = Product(identifier, jenkinsResourceIdentifier, "Kohsuke Kawaguchi", 2010, listOf(jenkinsRemotingComponent), listOf(nameAttribute, mainLanguageAttribute), null, null)
        return listOf(jenkins)
    }

    override fun addProduct(product: Product) {
        var productToSave = product
        if (product.identifier.isNullOrBlank()) {
            productToSave = product.copy(identifier = UUID.randomUUID().toString())
        }
        productRepository.save(productToSave)
    }

    override fun getAllProducts(): List<Product> {
        return productRepository.findAll().toList()
    }
}