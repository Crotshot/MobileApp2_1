package wit.assignments.mobapp2_1.api
//
//import com.google.gson.GsonBuilder
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object MarkClient {
//
//    val serviceURL = "https://Markweb-hdip-server.herokuapp.com"
//
//    fun getApi() : MarkService {
//
//        val gson = GsonBuilder().create()
//
//        val okHttpClient = OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()
//
//        val apiInterface = Retrofit.Builder()
//            .baseUrl(serviceURL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okHttpClient)
//            .build()
//        return apiInterface.create(MarkService::class.java)
//    }
//}
