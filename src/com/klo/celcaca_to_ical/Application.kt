package com.klo.celcaca_to_ical

import com.klo.celcaca_to_ical.misc.DateTimeAdapter
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.BadRequestException
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.response.header
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.ul
import org.joda.time.DateTime
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    install(CallLogging)
    install(Koin) {
        modules(celcatModule)
        modules(icalModule)
    }
    install(ContentNegotiation) {
        gson {
            registerTypeAdapter(DateTime::class.java, DateTimeAdapter())
        }
    }

    routing {

        val service: CelcatService by inject()
        val builder: IcalBuilder by inject()

        get("test") {
            log.debug(call.request.queryParameters.getAll("test").toString())
        }

        get("/ical/{formation?}") {

            //Paramètre formation
            val formationName = call.parameters["formation"] ?: throw BadRequestException("Manque la formation")
            val filtreYes = call.request.queryParameters.getAll("filtre_yes")
            val filtreNo = call.request.queryParameters.getAll("filtre_no")

            val events = when {
                filtreYes != null && filtreNo != null -> throw Exception("Utilisation des deux filtres :/")
                filtreYes != null -> {
                    val categories = filtreYes.map { EventCategory.valueOf(it) }.toTypedArray()
                    service.getEventsFromCategories(formationName, *categories)
                }
                filtreNo != null -> {
                    val categories = filtreNo.map { EventCategory.valueOf(it) }.toTypedArray()
                    service.getEventsWithoutCategories(formationName, *categories)
                }
                else -> service.getEvents(formationName)
            }


            val ical = builder.buildIcal(events)

            call.response.header("Content-Disposition", "attachment; filename=\"${ical.name}\"")
            call.respondFile(ical)
            ical.delete()

        }

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }
    }
}
