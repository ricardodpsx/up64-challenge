package com.up42.challenge

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

data class Feature(val id:UUID)

@RestController
class FeatureController {

  private val logger = KotlinLogging.logger {}

  @GetMapping("/features")
  @ResponseBody
  fun features() = listOf<Feature>(
    Feature(UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea"))
  )

}