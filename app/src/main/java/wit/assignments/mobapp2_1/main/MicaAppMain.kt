package wit.assignments.mobapp2_1.main

import android.app.Application
import timber.log.Timber
import wit.assignments.mobapp2_1.models.MarkMemStore
import wit.assignments.mobapp2_1.models.MarkStore

class MicaAppMain : Application() {

    lateinit var markStore: MarkStore

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        markStore = MarkMemStore()
        Timber.i("MICA Started")
    }
}