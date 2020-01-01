package com.up42.challenge

import org.assertj.core.api.Assertions
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

fun isValidImage(image: ByteArray) {
  val stream = ImageIO.createImageInputStream(ByteArrayInputStream(image))
  val readers = ImageIO.getImageReaders(stream)
  Assertions.assertThat(readers.next().formatName).isEqualTo("png")
}