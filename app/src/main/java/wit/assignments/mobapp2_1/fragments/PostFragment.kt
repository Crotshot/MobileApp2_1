package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.FragmentMapBinding
import wit.assignments.mobapp2_1.databinding.FragmentPostBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkModel

class PostFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentPostBinding? = null
    private val fragBinding get() = _fragBinding!!

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
        layout.imageButton.setOnClickListener {
            Toast.makeText(
                context, "Taking Picture",
                Toast.LENGTH_SHORT
            ).show()
            //TODO -> Assignment 2 add image
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
        val username : String = if(anon)
            "Anonymous"
        else
            "Bob" //TODO -> Assingment 2 get username

        if(markText.isEmpty()) {//TODO-> Assingment 2 add sensoring of poor language
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
            MarkModel(
                messageText = markText,
                userName = username
            )
        )

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
}