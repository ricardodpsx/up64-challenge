package com.up42.challenge.web

import com.up42.challenge.entity.Feature
import com.up42.challenge.featureFixture
import com.up42.challenge.isValidImage
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeatureControllerTest {

  @LocalServerPort
  private val port: Int = 0

  @Autowired
  lateinit var restTemplate: TestRestTemplate

  fun featureUrl(path: String = "") = "http://localhost:$port/features/$path"

  @Test
  fun `Should return a list of features`() {
    assertThat(restTemplate.getForObject(featureUrl(), List::class.java))
      .isNotEmpty
  }

  @Test
  fun `Should return a single Feature by id`() {
    assertThat(
      restTemplate
        .getForObject(featureUrl("39c2f29e-c0f8-4a39-a98b-deed547d6aea"), Feature::class.java)
    )
      .isEqualTo(featureFixture)
  }

  @Test
  fun `Should return a valid image`() {
    val response = restTemplate
      .getForEntity(featureUrl("39c2f29e-c0f8-4a39-a98b-deed547d6aea/quicklook"), ByteArray::class.java)
    isValidImage(response.body!!)
  }

  @Test
  fun `Should respond not found`() {
    val reponse = restTemplate.getForEntity(featureUrl("90c2f29e-c0f8-4a39-a98b-deed547d6aeb"), String::class.java)
    assertThat(reponse.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
  }

  @Test
  fun `Should respond with validation error if the UUID format is incorrect`() {
    val reponse = restTemplate.getForEntity(featureUrl("invalid-uuid"), String::class.java)
    assertThat(reponse.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
  }

}