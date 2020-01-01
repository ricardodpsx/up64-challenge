package com.up42.challenge

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.UUID

class FeatureStoreTest {

  @Test
  fun `can obtain a feature`() {
    val featureRepository = FeatureStore()
    assertThat(featureRepository.loadFeature(UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")))
      .isEqualTo(
        Feature(
          id = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea"),
          timestamp = 1554831167697,
          beginViewingDate = 1554831167697,
          endViewingDate = 1554831202043,
          missionName = "Sentinel-1B"
        )
      )
  }
}

