package com.up42.challenge

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FeatureController(@Autowired val featureStore: FeatureStore) {

  private val logger = KotlinLogging.logger {}

  @GetMapping("/features")
  @ResponseBody
  fun features() = featureStore.listOfFeatures()

}