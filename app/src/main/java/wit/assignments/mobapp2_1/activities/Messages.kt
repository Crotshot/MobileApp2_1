package wit.assignments.mobapp2_1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.adapters.MarkAdapter
import wit.assignments.mobapp2_1.databinding.ActivityMessagesBinding
import wit.assignments.mobapp2_1.main.MicaAppMain

class Messages : AppCompatActivity() {

    lateinit var app: MicaAppMain
    lateinit var messagesLayout : ActivityMessagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messagesLayout = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(messagesLayout.root)

        app = this.application as MicaAppMain
        messagesLayout.recyclerView.layoutManager = LinearLayoutManager(this)
        messagesLayout.recyclerView.adapter = MarkAdapter(app.markStore.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_messages, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_main -> { startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}