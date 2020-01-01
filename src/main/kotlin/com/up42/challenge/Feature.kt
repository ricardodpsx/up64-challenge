package com.up42.challenge

import java.util.UUID

data class Feature(
  val id: UUID,
  val timestamp: Long,
  val beginViewingDate: Long,
  val endViewingDate: Long,
  val missionName: String
)