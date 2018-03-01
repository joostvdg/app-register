package com.github.joostvdg.appregister.controller

import com.github.joostvdg.appregister.model.Product
import com.github.joostvdg.appregister.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(val productService: ProductService) {

    @GetMapping("/product")
    fun helloKotlin(): List<Product> {
        return productService.getAllProducts()
    }
}