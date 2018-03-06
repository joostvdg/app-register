package com.github.joostvdg.appregister.controller


import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import io.restassured.builder.RequestSpecBuilder

class VersionControllerTest {

    @Test
    fun testVersionController() {
        val spec = RequestSpecBuilder().setBaseUri("http://backend:8888").build()
            given().spec(spec)
                    .`when`().
                get("/version").
                then().
                statusCode(200).
               body("version", equalTo("0.1.0"))
    }
}