package com.up42.challenge

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.Base64
import java.util.UUID

class FeatureStoreTest {

  private val loadedFeature = FeatureStore().find(UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea"))!!

  @Test
  fun `can obtain a feature`() {
    assertThat(loadedFeature.copy(quicklook = null)).isEqualTo(featureFixture)
  }

  @Test
  fun `image is valid`() {
    assertThat(loadedFeature.quicklook).startsWith("iVBORw0KGgoAAAANSUhEUgAAAgAAAAHRCAIAAA")
    isValidImage(Base64.getDecoder().decode(loadedFeature.quicklook))
  }
}