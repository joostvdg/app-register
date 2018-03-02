package com.github.joostvdg.appregister.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "products")
@TypeAlias("product")
data class Product(@Id val identifier: String?, val resourceIdentifier: ResourceIdentifier, val author: String, val created: Int, var components: List<Component>?, var attributes: List<Attribute>?)