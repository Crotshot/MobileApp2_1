package wit.assignments.mobapp2_1.models

import android.graphics.Bitmap
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class MarkModel (var id: Long = 0,
                      var messageText : String = ". . .",
                      //-->>::var messageImage : Bitmap?,
                      var userName : String = "",
                      var views : Int = 0,
                      var goodRatings : Int = 0,
                      var poorRatings : Int = 0) : Parcelable

{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "messageText" to messageText,
            //-->>::"messageImage" to messageImage,
            "userName" to userName,
            "views" to views,
            "goodRatings" to goodRatings,
            "poorRatings" to poorRatings
        )
    }
}
//@Parcelize
//data class MarkModel (var id: String = "N/A",
//                      var messageText : String = ". . .",
//                      var messageImage : Bitmap?,
//                      var userName : String = "",
//                      var views : Int = 0,
//                      var goodRatings : Int = 0,
//                      var poorRatings : Int = 0) : Parcelable