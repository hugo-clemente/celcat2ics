package com.klo.celcaca_to_ical

import org.joda.time.DateTime
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

val yearStartDate = DateTime(2019, 9, 2, 0, 0)
val yearEndDate = DateTime(2020, 10, 1, 0, 0)

interface CelcatAPIService {

    @FormUrlEncoded
    @POST("GetCalendarData")
    fun getEvents(
        @Field("federationIds") formationName: String,
        @Field("start") start: String,
        @Field("end") end: String,
        @Field("resType") resType: Int = 103,
        @Field("calView") calView: String = "month"
    ): Call<List<CelcatEvent>>
}

class CelcatService(private val api: CelcatAPIService) {

    private val stringFormat = "yyyy-MM-dd"

    fun getData(formation: String): List<CelcatEvent>? = api.getEvents(
        formationName = formation,
        start = yearStartDate.toString(stringFormat),
        end = yearEndDate.toString(stringFormat)
    ).execute().body()

}

data class CelcatEvent(
    var allDay: Boolean?,
    var backgroundColor: String?,
    var custom1: String?,
    var custom2: String?,
    var custom3: String?,
    var department: String?,
    var description: String?,
    var end: String?,
    var eventCategory: String?,
    var faculty: String?,
    var id: String?,
    var module: String?,
    var registerStatus: String?,
    var site: String?,
    var start: String?,
    var studentMark: String?,
    var textColor: String?
) {
    fun toEvent(): Event {

        val detailsTab = mutableListOf<String>()
        val descripCelcatPropre = (description
            //encodage de merde
            ?.replace("&#39;", "'")
            ?.replace("&#176;", "°")
            ?.replace("&#232;", "è")
            ?.replace("&#233;", "é")
            ?.lines()
            ?.filter { it != "" && it != "<br />" }
            ?: throw Exception("Pas de description")).toMutableList()


        descripCelcatPropre.forEach { line ->
            line.replace("<br />", " || ").let {
                when {
                    it.contains("Salle") -> site += " | $it"
                    it.contains("FSI /") -> site += " | " + it.replace("FSI / ", "")
                    it.contains("M2") -> ""

                    else -> {
                        var newString = it

                        //region Removing redundant UE name
                        // Format : E_____ [E______ - Nom UE ]
                        //Cleaning strings
                        val indexBracket = newString.indexOf('[')

                        if (indexBracket != -1) {
                            val indexDash = newString.indexOf('-')

                            newString = if (indexDash != -1)
                                newString.removeRange(0..indexDash + 1)
                            else
                                newString.removeRange(0..indexBracket + 1)

                            newString = newString.dropLast(1)
                        }
                        //endregion

                        detailsTab.add(newString)
                    }
                }
            }

        }


        val details = detailsTab.joinToString(separator = " - \r\n ")

        return Event(
            uid = id ?: UUID.randomUUID().toString(),
            start = start?.let { DateTime.parse(it) } ?: throw Exception("No start for this event ??"),
            //S'il n'y a pas de fin à l'event, alors on suppose qu'il dure toute la journée
            end = end?.let { DateTime.parse(it) } ?: start.let { DateTime.parse(it) }.plusDays(1),
            dtstamp = DateTime.now(),
            location = site ?: "",
            description = details,
            name = eventCategory ?: ""
        )

    }
}