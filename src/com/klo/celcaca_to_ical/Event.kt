package com.klo.celcaca_to_ical

import org.joda.time.DateTime

data class Event(
    val uid: String,
    val start: DateTime,
    val end: DateTime,
    val dtstamp: DateTime,
    val location: String,
    val description: String,

    val category: EventCategory
)

enum class EventCategory {
    //peut être TD | TP | COURS | EXAMEN | TP non encadré | le reste (tout ce qu'il leur passe par la tête -_-)
    TD,
    TP,
    COURS,
    EXAMEN,
    TP_NON_ENCADRE,
    CONGES,
    FERIE,
    OTHER;

    override fun toString(): String {
        return name
    }
}