package wit.assignments.mobapp2_1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.ActivityMainBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkModel

class MainActivity : AppCompatActivity() {
    lateinit var app: MicaAppMain
    private lateinit var mainLayout : ActivityMainBinding
    var buttonPresses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = this.application as MicaAppMain

        mainLayout = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainLayout.root)

        mainLayout.testButton.setOnClickListener {
            buttonPresses++
            Toast.makeText(applicationContext,"Button has been pressed $buttonPresses times!",Toast.LENGTH_SHORT).show()
            app.markStore.create(MarkModel(messageText = "Press $buttonPresses", userName = "Bob", views = 342432, goodRatings = 342, poorRatings = 1))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_messages -> { startActivity(Intent(this, Messages::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}