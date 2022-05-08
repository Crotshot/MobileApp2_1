package wit.assignments.mobapp2_1.activities

import android.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.ActivityHomeBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkJSONStore

class HomeActivity : AppCompatActivity(){
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var activityHomeBinding : ActivityHomeBinding

    private val userName : String = "Ash"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        drawerLayout = activityHomeBinding.drawerLayout

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        val navView = activityHomeBinding.navView
        navView.setupWithNavController(navController)

        val leaveButton =  navView.menu.findItem(R.id.leave)
        leaveButton.setOnMenuItemClickListener {
            setResult(10)
            finish()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    fun getUserName() : String{
        return userName
    }
}