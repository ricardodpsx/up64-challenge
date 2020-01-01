package com.up42.challenge.web

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class HttpExceptionHandler : ResponseEntityExceptionHandler() {
  companion object : KLogging()

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception::class)
  fun internalServerErrorHandler(exception: Exception): ResponseEntity<Any> {
    logger.error(exception.message, exception)
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException::class)
  fun handleValidationException(exception: IllegalArgumentException, request: WebRequest): ResponseEntity<String> {
    logger.warn("Validation Exception! - ${(request as ServletWebRequest).request.requestURI} $exception")
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException::class)
  fun handleNoSuchElementException(exception: NoSuchElementException, request: WebRequest): ResponseEntity<Nothing> {
    logger.warn("Not found! - ${(request as ServletWebRequest).request.requestURI} $exception")
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
  }
}
