package wit.assignments.mobapp2_1.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import wit.assignments.mobapp2_1.helpers.read
import wit.assignments.mobapp2_1.helpers.write
import java.lang.reflect.Type
import java.util.*
import java.io.Serializable

const val JSON_FILE = "marks.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()

val listType: Type = object : TypeToken<ArrayList<MarkModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MarkJSONStore(private val context: Context) : MarkStore, Serializable{
    var marks = ArrayList<MarkModel>()

    override fun start(){
        deserialize()
    }

    override fun findAll(): List<MarkModel> {
        return marks
    }

    override fun findById(id:Long) : MarkModel? {
        val foundmark: MarkModel? = marks.find { it.id == id }
        return foundmark
    }

    override fun create(mark: MarkModel) {
        mark.id = generateRandomId()
        marks.add(mark)
        logAll()
        serialize()
    }

    override fun update(mark: MarkModel) {
        val foundMark: MarkModel? = marks.find { p -> p.id == mark.id }
        if (foundMark != null) {
            foundMark.poorRatings = mark.poorRatings
            foundMark.goodRatings = mark.goodRatings
            foundMark.views = mark.views
            foundMark.messageText = mark.messageText
            foundMark.userName = mark.userName
            logAll()
            serialize()
        }
    }

    override fun destroy(mark: MarkModel) {
        mark.id = getId()
        marks.remove(mark)
        logAll()
        serialize()
    }

    fun logAll() {
        Timber.v("** Donation List **")
        marks.forEach { Timber.v("Donate ${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(marks, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        marks = gsonBuilder.fromJson(jsonString, listType)
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}