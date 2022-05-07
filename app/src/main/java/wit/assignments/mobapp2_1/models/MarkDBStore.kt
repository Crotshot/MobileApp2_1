package wit.assignments.mobapp2_1.models

import timber.log.Timber

class MarkDBStore : MarkStore{
    val knownmarks = ArrayList<MarkModel>()

    override fun start() {

    }

    override fun findAll(): List<MarkModel> {
        return knownmarks
    }

    override fun findById(id:Long) : MarkModel? {
        return null
    }

    override fun create(mark: MarkModel) {

    }

    override fun destroy(mark: MarkModel) {

    }

    override fun update(mark: MarkModel) {

    }

    override fun filterFind(filter: String): List<MarkModel> {
        return knownmarks
    }

    fun logAll() {

    }
}