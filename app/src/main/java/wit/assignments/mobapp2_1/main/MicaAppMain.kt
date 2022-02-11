package wit.assignments.mobapp2_1.main

import android.app.Application
import timber.log.Timber
import wit.assignments.mobapp2_1.models.MarkJSONStore
import wit.assignments.mobapp2_1.models.MarkMemStore
import wit.assignments.mobapp2_1.models.MarkStore
import java.io.Serializable

class MicaAppMain : Application(), Serializable {

    lateinit var markStore: MarkStore

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        markStore = MarkJSONStore(applicationContext)
        markStore.start()
        Timber.i("MICA Started")
    }
}