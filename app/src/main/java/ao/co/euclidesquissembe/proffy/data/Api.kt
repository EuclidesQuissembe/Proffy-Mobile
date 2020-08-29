package ao.co.euclidesquissembe.proffy.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.42.0.1/proffy/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: Interface = initRetrofit().create(Interface::class.java)
}