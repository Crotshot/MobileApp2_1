package wit.assignments.mobapp2_1.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarkModel (var id: Long = 0,
                      val messageText : String = ". . .",
                      val userName : String = "",
                      var views : Int = 0,
                      var goodRatings : Int = 0,
                      var poorRatings : Int = 0) : Parcelable