package wit.assignments.mobapp2_1.models

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.io.Serializable
import java.util.HashMap

class MarkDBStore(private val context: Context) : MarkStore, Serializable {
    val knownmarks = ArrayList<MarkModel>()
    lateinit var database : DatabaseReference
    lateinit var user: String

    override fun start() {
        database = Firebase.database("https://mica-340917-default-rtdb.europe-west1.firebasedatabase.app/").reference
        user = FirebaseAuth.getInstance().currentUser?.email.toString()
//        val myRef = database.getReference("message")
//        myRef.setValue("Hello, World!")
//        // Read from the database
//        myRef.addValueEventListener(object: ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = snapshot.getValue<String>()
//                Timber.i("Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Timber.i("Failed to read value. $error.toException()")
//            }
//
//        })
    }

//    override fun findAll(): List<MarkModel> {
//        return knownmarks //Unused
//    }

    override fun findById(id:Long) : MarkModel? {
        if(knownmarks.isEmpty()){
            filterFind("")
        }
        val foundmark: MarkModel? = knownmarks.find { it.id == id }
        return foundmark
    }

    override fun create(mark: MarkModel) {
        //database.child("Marks").child("H5rfjTWl1SOsX9FbXe4z").child("userName").setValue("Hello!")

        val key = database.child("Marks").push().key
        if(key == null){
            Timber.i("Unable to retrieve push key for Marks")
            return
        }

        val markValues = mark.toMap()

        val childUpdates = HashMap<String, Any>()
        childUpdates["/Marks/$key"] = markValues
        //childUpdates["/User-Marks/$user/$key"] = markValues

        database.updateChildren(childUpdates)
    }

    override fun destroy(mark: MarkModel) {

    }

    override fun update(mark: MarkModel) {
        val foundMark: MarkModel? = knownmarks.find { p -> p.id == mark.id }
        if (foundMark != null) {
            foundMark.poorRatings = mark.poorRatings
            foundMark.goodRatings = mark.goodRatings
            foundMark.views = mark.views
            foundMark.messageText = mark.messageText
            //-->>::foundMark.messageImage = mark.messageImage
            foundMark.userName = mark.userName


            val foundmarkValue = foundMark.toMap()
            val childUpdate : MutableMap<String, Any?> = HashMap()
            childUpdate["Marks/$foundMark.id"] = foundmarkValue
            database.updateChildren(childUpdate)

            logAll()
        }
    }

    override fun filterFind(filter: String): List<MarkModel> {
        val snapshot  = database.child("Marks").get()
        Thread.sleep(2000)
        val res : DataSnapshot = snapshot.result
        Timber.i("Data: $res")
        val children = res.children

        val marksList = ArrayList<MarkModel>()
        children.forEach {
            val mark = it.getValue<MarkModel>(MarkModel::class.java)
            marksList.add(mark!!)
        }

        for (markModel in marksList) {
            Timber.i(markModel.messageText)
        }

        knownmarks.clear()
        knownmarks.addAll(marksList)

        if(filter.isEmpty())
            return marksList

        var tMarks : List<MarkModel> = emptyList()

        if(filter.first() == '!' && filter.length > 1){
            if(filter[1] == '@' && filter.length > 2){
                val name = filter.substring(2,filter.lastIndex) //Filter by user name
                for (mark : MarkModel in marksList) {
                    if (!mark.userName.contains(name)) {
                        tMarks += mark
                    }
                }
            }
            else {
                val name = filter.substring(1, filter.lastIndex)
                for (mark: MarkModel in marksList) {
                    if (!mark.messageText.contains(name)) { //Filter by message
                        tMarks += mark
                    }
                }
            }
        }
        else if(filter.first() == '@' && filter.length > 1){
            val name = filter.substring(1,filter.lastIndex) //Filter by user name
            for (mark : MarkModel in marksList) {
                if (mark.userName.contains(name) || filter == "") {
                    tMarks += mark
                }
            }
        }
        else
            for (mark : MarkModel in marksList) {
                if (mark.messageText.contains(filter)) { //Filter by message
                    tMarks += mark
                }
            }
        return tMarks
    }

    fun logAll() {
        Timber.v("List of all known Marks")
        knownmarks.forEach { Timber.v("Mark: ${it}") }
    }
}