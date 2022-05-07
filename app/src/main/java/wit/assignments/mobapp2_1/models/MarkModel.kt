package wit.assignments.mobapp2_1.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarkModel (var id: Long = 0,
                      var messageText : String = ". . .",
                      var messageImage : Bitmap?,
                      var userName : String = "",
                      var views : Int = 0,
                      var goodRatings : Int = 0,
                      var poorRatings : Int = 0) : Parcelable

//@Parcelize
//data class MarkModel (var id: String = "N/A",
//                      var messageText : String = ". . .",
//                      var messageImage : Bitmap?,
//                      var userName : String = "",
//                      var views : Int = 0,
//                      var goodRatings : Int = 0,
//                      var poorRatings : Int = 0) : Parcelable