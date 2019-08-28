package com.klo.celcaca_to_ical

import org.joda.time.DateTime

data class Event(
    val uid: String,
    val start: DateTime,
    val end: DateTime,
    val dtstamp: DateTime,
    val location: String,
    val description: String,
    val name: String
)