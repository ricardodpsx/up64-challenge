package com.up42.challenge

import mu.KotlinLogging
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class FeatureController(@Autowired val featureStore: FeatureStore) {
  private val logger = KotlinLogging.logger {}

  @GetMapping("/features")
  @ResponseBody
  fun features() = featureStore.list()

  @GetMapping("/features/{id}")
  @ResponseBody
  fun feature(@PathVariable id: String) = featureStore.find(UUID.fromString(id))

  @GetMapping("/features/{id}/quicklook", produces = [MediaType.IMAGE_PNG_VALUE])
  @ResponseBody
  fun featureQuickLook(@PathVariable id: String): ByteArray =
    Base64.decodeBase64(featureStore.find(UUID.fromString(id))!!.quicklook)
}