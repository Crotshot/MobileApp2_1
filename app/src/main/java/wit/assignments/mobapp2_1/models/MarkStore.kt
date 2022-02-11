package wit.assignments.mobapp2_1.models

interface MarkStore {
    fun start()
    fun findAll() : List<MarkModel>
    fun findById(id: Long) : MarkModel?
    fun create(mark: MarkModel)
    fun destroy(mark: MarkModel)
    fun update(mark: MarkModel)
}