package wit.assignments.mobapp2_1.models

import androidx.lifecycle.MutableLiveData

interface MarkStore {
    fun start()
    fun findAll() : List<MarkModel>
    fun findById(id: Long) : MarkModel?
    fun create(mark: MarkModel)
    fun destroy(mark: MarkModel)
    fun update(mark: MarkModel)
    fun filterFind(filter: String) : List<MarkModel>

//    fun findAll(markList:
//                MutableLiveData<List<MarkModel>>
//    )
//    fun findById(id: String) : MarkModel?
//    fun create(donation: MarkModel)
//    fun delete(id: String)
////    fun update(id: String)
////    fun filterFind(filter: String) : List<MarkModel>
}