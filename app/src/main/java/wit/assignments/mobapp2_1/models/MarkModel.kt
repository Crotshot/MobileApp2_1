package wit.assignments.mobapp2_1.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarkModel (var id: Long = 0,
                      val messageText : String = ". . .",
                      val userName : String = "",
                      val views : Int = 0,
                      val goodRatings : Int = 0,
                      val poorRatings : Int = 0) : Parcelable