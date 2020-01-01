package com.up42.challenge.store

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.util.UUID

@Component
class FeatureStore {
  private val byId by lazy { list().associateBy({ it.id }, { it }) }

  fun find(id: UUID) = byId[id] ?: throw FeatureNotFound("Feature '$id' not found")

  fun list() = loadJson().map { it["features"] }.flatten().map { it.toFeature() }

  private fun loadJson() = ObjectMapper()
    .readTree(ResourceUtils.getFile("classpath:source-data.json"))
}

class FeatureNotFound(message: String) : NoSuchElementException(message)
