package wit.assignments.mobapp2_1.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.activities.HomeActivity
import wit.assignments.mobapp2_1.databinding.FragmentPostBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkModel


class PostFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentPostBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val REQUEST_IMAGE_CAPTURE = 101
    lateinit var imageButt : ImageButton
    private var bitMap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MicaAppMain
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentPostBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_post)

        setButtonListeners(fragBinding)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostFragment().apply {
                arguments = Bundle().apply { }
            }
    }


    fun setButtonListeners(layout: FragmentPostBinding) {
        imageButt = layout.imageButton
        layout.imageButton.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }
        layout.cancelButton.setOnClickListener {
            leaveFrag()
        }
        layout.postButton.setOnClickListener {
            post(false, layout)
        }
        layout.postAnonButton.setOnClickListener {
            post(true, layout)
        }
    }

    fun post(anon : Boolean,layout: FragmentPostBinding){
        val markText : String = layout.postText.text.toString()
        val homeActivity : HomeActivity = activity as HomeActivity

        val username : String = if(anon)
            "Anonymous"
        else
            homeActivity.getUserName()

        if(markText.isEmpty()) {//TODO-> Assignment 2 add sensoring of poor language
            Toast.makeText(
                context, "Mark text empty, please enter some text",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        Toast.makeText(
            context, "Posting as $username",
            Toast.LENGTH_SHORT
        ).show()

        app.markStore.create(
            //var conf : Bitmap.Config = Bitmap.Config.ARGB_8888; // see other conf types
            MarkModel(
                messageText = markText,
                userName = username,
                //messageImage = bitMap
            )
        )

        leaveFrag()
    }

    fun leaveFrag(){
        val manager : FragmentManager = requireFragmentManager()
        val fragment = MapFragment()
        val transaction = manager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_messages, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val extras = data?.extras
            bitMap= extras?.get("data") as Bitmap
            imageButt.setImageBitmap(bitMap)
            print(bitMap)
        }
    }
}