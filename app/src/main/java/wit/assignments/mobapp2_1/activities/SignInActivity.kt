package wit.assignments.mobapp2_1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wit.assignments.mobapp2_1.databinding.ActivitySigninBinding


class SignInActivity : AppCompatActivity(){
    private lateinit var activitySigninBinding : ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySigninBinding = ActivitySigninBinding.inflate(layoutInflater)

    }
}