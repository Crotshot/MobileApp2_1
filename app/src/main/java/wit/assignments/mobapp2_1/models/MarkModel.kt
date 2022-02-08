package wit.assignments.mobapp2_1.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarkModel (var id: Long = 0,
                      val messageText : String = ". . .",
                      val userName : String = "") : Parcelable/*,
                      val posRatings : Int = 0,
                      val negRatings : Int = 0) : Parcelable*/