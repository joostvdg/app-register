package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.model.product.Product
import com.github.joostvdg.appregister.model.ResourceIdentifier
import com.github.joostvdg.appregister.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(val productService: ProductService) {

    @GetMapping("/product")
    fun getAll(): List<Product> {
        return productService.getAllProducts()
    }

    @PostMapping("/product")
    fun addProduct(@RequestBody product: Product) {
        productService.addProduct(product)
    }

    @GetMapping("/product/resourceIdentifier/maven")
    fun getResourceIdentifierForMavenGAV(groupId: String, artifactId: String, version: String): ResourceIdentifier {
        return ResourceIdentifier.fromMavenGav(groupId, artifactId, version)
    }
}