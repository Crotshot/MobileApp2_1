package wit.assignments.mobapp2_1.models

interface MarkStore {
    fun findAll() : List<MarkModel>
    fun findById(id: Long) : MarkModel?
    fun create(mark: MarkModel)
    fun destroy(mark: MarkModel)
}