package com.github.joostvdg.appregister.repository

import com.github.joostvdg.appregister.model.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, String>