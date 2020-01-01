package com.up42.challenge

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort



@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeatureControllerTest {

  @LocalServerPort
  private val port: Int = 0

  @Autowired
  lateinit var restTemplate: TestRestTemplate

  @Test
  fun `Should return a list of features`() {
    assertThat(restTemplate.getForObject("http://localhost:$port/features", List::class.java))
      .isNotEmpty
  }
}