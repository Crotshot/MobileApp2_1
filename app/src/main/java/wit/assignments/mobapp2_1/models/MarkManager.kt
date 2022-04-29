package wit.assignments.mobapp2_1.models

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import wit.assignments.mobapp2_1.api.MarkClient
import wit.assignments.mobapp2_1.api.MarkWrapper

object MarkManager : MarkStore {

    private var Marks = ArrayList<MarkModel>()

    override fun findAll(MarksList: MutableLiveData<List<MarkModel>>) {

        val call = MarkClient.getApi().getall()

        call.enqueue(object : Callback<List<MarkModel>> {
            override fun onResponse(call: Call<List<MarkModel>>,
                                    response: Response<List<MarkModel>>
            ) {
                MarksList.value = response.body() as ArrayList<MarkModel>
                Timber.i("Retrofit JSON = ${response.body()}")
            }

            override fun onFailure(call: Call<List<MarkModel>>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun findById(id:String) : MarkModel? {
        val foundMark: MarkModel? = Marks.find { it.id == id }
        return foundMark
    }

    override fun create(Mark: MarkModel) {

        val call = MarkClient.getApi().post(Mark)

        call.enqueue(object : Callback<MarkWrapper> {
            override fun onResponse(call: Call<MarkWrapper>,
                                    response: Response<MarkWrapper>
            ) {
                val MarkWrapper = response.body()
                if (MarkWrapper != null) {
                    Timber.i("Retrofit ${MarkWrapper.message}")
                    Timber.i("Retrofit ${MarkWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<MarkWrapper>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun delete(id: String) {
        val call = MarkClient.getApi().delete(id)

        call.enqueue(object : Callback<MarkWrapper> {
            override fun onResponse(call: Call<MarkWrapper>,
                                    response: Response<MarkWrapper>
            ) {
                val MarkWrapper = response.body()
                if (MarkWrapper != null) {
                    Timber.i("Retrofit Delete ${MarkWrapper.message}")
                    Timber.i("Retrofit Delete ${MarkWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<MarkWrapper>, t: Throwable) {
                Timber.i("Retrofit Delete Error : $t.message")
            }
        })
    }
}