package com.up42.challenge.store

import com.fasterxml.jackson.databind.JsonNode
import com.up42.challenge.entity.Feature
import java.util.UUID

fun JsonNode.toFeature() = Feature(
  id = UUID.fromString(at("/properties/id").asText()),
  timestamp = at("/properties/timestamp").asLong(),
  beginViewingDate = at("/properties/acquisition/beginViewingDate").asLong(),
  endViewingDate = at("/properties/acquisition/endViewingDate").asLong(),
  missionName = at("/properties/acquisition/missionName").asText(),
  quicklook = at("/properties/quicklook").asText()
)