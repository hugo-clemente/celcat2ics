package com.klo.celcaca_to_ical

import java.io.File

class IcalBuilder {

    private val dateFormat = "yyyyMMdd'T'HHmmss'Z'"

    fun buildIcal(events: List<Event>) =
        File("calendar.ics").apply {
            createNewFile()
            printWriter().use {
                it.println("BEGIN:VCALENDAR")
                it.println("VERSION:2.0")
                it.println("PRODID:-//hacksw/handcal//NONSGML v1.0//EN")

                for (event in events) {

                    if (event.name in arrayOf("CONGES", "FERIE"))
                        continue

                    it.println("BEGIN:VEVENT")
                    it.println("UID:" + event.uid)
                    it.println("DTSTAMP:" + event.dtstamp.toString(dateFormat))
                    it.println("DTSTART:" + event.start.toString(dateFormat))
                    it.println("DTEND:" + event.end.toString(dateFormat))
                    it.println("LOCATION:" + event.location)
                    it.println("DESCRIPTION:")
                    it.println("SUMMARY:" + correctString(event.description))
                    it.println("TRANSP:OPAQUE")
                    it.println("FBTYPE:BUSY-UNAVAILABLE")
                    it.println("END:VEVENT")
                }

                it.print("END:VCALENDAR")

            }
        }

    private fun correctString(s: String): String {
        return s.trimEnd()
    }

}