package com.github.joostvdg.appregister.model

data class Product(var author: String, var created: Int, var identifier: ResourceIdentifier, var components: List<Component>, var attributes: List<Attribute>)