package wit.assignments.mobapp2_1.models

import timber.log.Timber

class MarkMemStore : MarkStore{
    val marks = ArrayList<MarkModel>()

    override fun start() {
    }

//    override fun findAll(): List<MarkModel> {
//        return marks
//    }

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

    override fun update(mark: MarkModel) {
        val foundMark: MarkModel? = marks.find { p -> p.id == mark.id }
        if (foundMark != null) {
            foundMark.poorRatings = mark.poorRatings
            foundMark.goodRatings = mark.goodRatings
            foundMark.views = mark.views
            foundMark.messageText = mark.messageText
            //-->>::foundMark.messageImage = mark.messageImage
            foundMark.userName = mark.userName
            logAll()
        }
    }

    override fun filterFind(filter: String): List<MarkModel> {
        return marks
    }

    fun logAll() {
        Timber.v("List of all known Marks")
        marks.forEach { Timber.v("Mark: ${it}") }
    }
}