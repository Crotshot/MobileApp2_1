package wit.assignments.mobapp2_1.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MarkMemStore : MarkStore{
    val marks = ArrayList<MarkModel>()

    override fun findAll(): List<MarkModel> {
        return marks
    }

    override fun findById(id:Long) : MarkModel? {
        val foundmark: MarkModel? = marks.find { it.id == id }
        return foundmark
    }

    override fun create(mark: MarkModel) {
        mark.id = getId()
        marks.add(mark)
        logAll()
    }

    override fun destroy(mark: MarkModel) {
        mark.id = getId()
        marks.remove(mark)
        logAll()
    }

    fun logAll() {
        Timber.v("** Donation List **")
        marks.forEach { Timber.v("Donate ${it}") }
    }
}