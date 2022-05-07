package wit.assignments.mobapp2_1.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(){
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var activityHomeBinding : ActivityHomeBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var user : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        auth = FirebaseAuth.getInstance()
        user = auth.currentUser?.email.toString()

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
        return user
    }
//
//    override  fun onOptionsItemSelected(menuItem : MenuItem) : Boolean{
//        Timber.w( "ID Pressed: $menuItem.itemId")
//        if(menuItem.itemId == R.id.leave) {
//            finish()
//        }
//        return super.onOptionsItemSelected(menuItem)
//    }
}
