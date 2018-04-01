package com.github.joostvdg.appregister.model.event

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.time.LocalDateTime

@Document(indexName = "events")
data class Event (@Id @JsonProperty("identifier")
                  var identifier: String?,
                  @JsonProperty("sourceIdentifier")
                  val sourceIdentifier: String,
                  @JsonProperty("targetIdentifier")
                  val targetIdentifier: String,
                  @JsonProperty("message")
                  val message: String,
                  @JsonProperty("type")
                  val type: EventType,
                  @JsonProperty("significance")
                  val significance: EventSignificance,
                  @JsonProperty("recorded")
                  var recorded: LocalDateTime?
)
