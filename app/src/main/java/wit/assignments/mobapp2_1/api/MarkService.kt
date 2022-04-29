package wit.assignments.mobapp2_1.api

import retrofit2.Call
import retrofit2.http.*
import wit.assignments.mobapp2_1.models.MarkModel


interface MarkService {
    @GET("/Marks")
    fun getall(): Call<List<MarkModel>>

    @GET("/Marks/{id}")
    fun get(@Path("id") id: String): Call<MarkModel>

    @DELETE("/Marks/{id}")
    fun delete(@Path("id") id: String): Call<MarkWrapper>

    @POST("/Marks")
    fun post(@Body Mark: MarkModel): Call<MarkWrapper>

    @PUT("/Marks/{id}")
    fun put(@Path("id") id: String,
            @Body Mark: MarkModel
    ): Call<MarkWrapper>
}

