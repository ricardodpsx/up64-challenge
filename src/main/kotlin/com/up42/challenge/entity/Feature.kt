package com.up42.challenge.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID

data class Feature(
  val id: UUID,
  val timestamp: Long,
  val beginViewingDate: Long,
  val endViewingDate: Long,
  val missionName: String,
  @JsonIgnore
  val quicklook: String?
)