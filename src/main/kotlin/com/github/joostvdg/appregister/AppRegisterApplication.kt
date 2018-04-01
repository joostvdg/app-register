package com.github.joostvdg.appregister

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.elasticsearch.client.Client
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.DefaultResultMapper
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.EntityMapper
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableDiscoveryClient
open class AppRegisterApplication

fun main(args: Array<String>) {
    runApplication<AppRegisterApplication>(*args)
}

@Bean
fun objectMapper(): ObjectMapper =
    ObjectMapper()
        .registerModule(JavaTimeModule())
        .registerModule(ParameterNamesModule())
        .registerModule(Jdk8Module())
        .registerModule(KotlinModule())

@Configuration
open class ElasticsearchConfig(val elasticsearchEntityMapper: ElasticsearchEntityMapper) {
    @Bean
    open fun elasticsearchTemplate(client: Client) = ElasticsearchTemplate(client, DefaultResultMapper(elasticsearchEntityMapper))
}

@Component
class ElasticsearchEntityMapper(val objectMapper: ObjectMapper) : EntityMapper {
    override fun mapToString(`object`: Any?) = objectMapper.writeValueAsString(`object`)
    override fun <T : Any?> mapToObject(source: String?, clazz: Class<T>?) = objectMapper.readValue(source, clazz)
}
