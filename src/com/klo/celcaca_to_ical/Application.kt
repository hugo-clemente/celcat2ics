package com.klo.celcaca_to_ical

import com.klo.celcaca_to_ical.misc.DateTimeAdapter
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.BadRequestException
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.http.withCharset
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
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
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {

        val service: CelcatService by inject()
        val builder: IcalBuilder by inject()

        get("/ical/{formation?}") {

            //Paramètre formation
            val formationName = call.parameters["formation"] ?: throw BadRequestException("Manque la formation")
            val filtreYes = call.request.queryParameters.getAll("filtre_yes")
            val filtreNo = call.request.queryParameters.getAll("filtre_no")

            val events = when {
                filtreYes != null && filtreNo != null -> throw Exception("Utilisation des deux filtres :/")
                filtreYes != null -> {
                    val categories = filtreYes.map { EventCategory.valueOf(it) }.toTypedArray()

                    log.debug("Selecting events from $formationName with categories : ${categories.map { it.toString() }}")

                    service.getEventsFromCategories(formationName, *categories)
                }
                filtreNo != null -> {
                    val categories = filtreNo.map { EventCategory.valueOf(it) }.toTypedArray()

                    log.debug("Selecting events from $formationName without categories : ${categories.map { it.toString() }}")

                    service.getEventsWithoutCategories(formationName, *categories)
                }
                else -> {
                    log.debug("Selecting every events from $formationName")
                    service.getEvents(formationName)
                }
            }


            val ical = builder.buildIcal(events)

            call.response.header("Content-Disposition", "attachment; filename=\"${ical.name}\"")
            call.respondFile(ical)
            ical.delete()

        }

        get("/urls") {
            call.respond(
                FreeMarkerContent(
                    "urls.ftl",
                    emptyMap<String, String>(),
                    contentType = ContentType.Text.Html.withCharset(Charsets.UTF_8)
                )
            )
        }

        static("/static") {
            resources("static")
        }

        get("/") {
            call.respond(
                FreeMarkerContent(
                    "index.ftl",
                    emptyMap<String, String>(),
                    contentType = ContentType.Text.Html.withCharset(Charsets.UTF_8)
                )
            )
        }
    }
}
