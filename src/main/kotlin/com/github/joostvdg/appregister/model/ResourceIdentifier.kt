package com.github.joostvdg.appregister.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

// https://github.com/grafeas/grafeas
//Component Type	Identifier	Example
//Debian	deb://dist(optional):arch:name:version	deb://lucid:i386:acl:2.2.49-2
//Docker	https://Namespace/name@sha256:	https://gcr.io/scanning-customer/dockerimage@sha256:244fd47e07d1004f0aed9c156aa09083c82bf8944eceb67c946ff7430510a77b
//Generic file	file://sha256::name	file://sha256:244fd47e07d1004f0aed9c156aa09083c82bf8944eceb67c946ff7430510a77b:foo.jar
//Maven	gav://group:artifact:version	gav://ant:ant:1.6.5
//NPM	npm://package:version	npm://mocha:2.4.5
//NuGet	nuget://module:version	nuget://log4net:9.0.1
//Python	pip://package:version	pip://raven:5.13.0
//RPM	rpm://dist(optional):arch:name:version	rpm://el6:i386:ImageMagick:6.7.2.7-4

@TypeAlias("url")
data class ResourceIdentifier (val type: ComponentType, @Id val url: String) {
    companion object {
        fun fromMavenGav(groupId: String, artifactId: String, version: String): ResourceIdentifier {
            //Maven	gav://group:artifact:version	gav://ant:ant:1.6.5
            // val s2 = "${s1.replace("is", "was")}, but now is $a"
            val gavCombination = "gav://$groupId:$artifactId:$version"
            return ResourceIdentifier(ComponentType.MAVEN, gavCombination)
        }
    }
}