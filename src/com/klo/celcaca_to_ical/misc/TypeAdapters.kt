package com.klo.celcaca_to_ical.misc

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.joda.time.DateTime

//TODO Utile ?

class DateTimeAdapter : TypeAdapter<DateTime>() {

    override fun write(out: JsonWriter?, value: DateTime?) {
        out?.value(value.toString())
    }

    override fun read(`in`: JsonReader?): DateTime {
        throw NotImplementedError("oui")
    }
}