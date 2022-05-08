package wit.assignments.mobapp2_1.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.ActivityMainBinding
import wit.assignments.mobapp2_1.main.MicaAppMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MicaAppMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Main Activity Created")

        setContentView(R.layout.activity_main)
        app = application as MicaAppMain

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val intent = Intent(this, SignInActivity::class.java)
        Timber.i("Opening Sign In Activity")
        startActivityForResult(intent,1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == 10){
            val intent = Intent(this, SignInActivity::class.java)
            Timber.i("Opening Sign In Activity")
            startActivityForResult(intent,2)
        }
        else{
            val intent = Intent(this, HomeActivity::class.java)
            Timber.i("Opening Home Activity")
            startActivityForResult(intent,2)
        }
//        if(data?.javaClass == SignInActivity::class.java && resultCode == RESULT_OK){
//            val intent = Intent(this, HomeActivity::class.java)
//            Timber.i("Opening Home Activity")
//            startActivityForResult(intent,2)
//        }
//        else if(data?.javaClass == HomeActivity::class.java ){
//            val intent = Intent(this, SignInActivity::class.java)
//            Timber.i("Opening Sign In Activity")
//            startActivityForResult(intent,1)
//        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}