package com.klo.celcaca_to_ical

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val celcatModule = module {

    single<CelcatAPIService> {
        val retro = Retrofit.Builder()
            .baseUrl("https://edt.univ-tlse3.fr/calendar2/Home/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retro.create(CelcatAPIService::class.java)
    }

    single {
        val api: CelcatAPIService by inject()
        CelcatService(api)
    }
}

val icalModule = module {

    single {
        IcalBuilder()
    }
}