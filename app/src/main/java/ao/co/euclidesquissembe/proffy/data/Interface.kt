package ao.co.euclidesquissembe.proffy.data

import ao.co.euclidesquissembe.proffy.data.response.FilterResponse
import ao.co.euclidesquissembe.proffy.data.response.ProffysResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Interface {

    @GET("proffys/{page}")
    suspend fun getAllProffys(
        @Path("page") page: Int,
        @Query("matter") matter: String = "",
        @Query("day") day: String = "",
        @Query("time") time: String = ""
    ): ProffysResponse

    @GET("filters")
    suspend fun getFilters(): FilterResponse
}