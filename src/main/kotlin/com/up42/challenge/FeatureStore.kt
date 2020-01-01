package com.up42.challenge

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.util.UUID

@Component
class FeatureStore {
  private val byId by lazy { list().associateBy({ it.id }, { it }) }

  fun find(id: UUID) = byId[id]

  private fun fromJsonNode(node: JsonNode) =
    Feature(
      id = UUID.fromString(node.at("/properties/id").asText()),
      timestamp = node.at("/properties/timestamp").asLong(),
      beginViewingDate = node.at("/properties/acquisition/beginViewingDate").asLong(),
      endViewingDate = node.at("/properties/acquisition/endViewingDate").asLong(),
      missionName = node.at("/properties/acquisition/missionName").asText(),
      quicklook = node.at("/properties/quicklook").asText()
    )

  fun list() = loadJson().map { it["features"] }.flatten().map(::fromJsonNode)

  private fun loadJson() = ObjectMapper()
    .readTree(ResourceUtils.getFile("classpath:source-data.json"))
}