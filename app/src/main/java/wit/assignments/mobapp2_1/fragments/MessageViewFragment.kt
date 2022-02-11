package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.FragmentMessageViewBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkJSONStore
import wit.assignments.mobapp2_1.models.MarkModel

class MessageViewFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentMessageViewBinding? = null
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
        _fragBinding = FragmentMessageViewBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_message)

        val markStore: MarkJSONStore = arguments?.get("markStore") as MarkJSONStore
        val markID : Long = arguments?.getLong("id") as Long
        val mark = markStore.findById(markID) as MarkModel

        fragBinding.userName.text = mark.userName
        fragBinding.goodRatingsText.text = mark.goodRatings.toString()
        fragBinding.poorRatingsText.text = mark.poorRatings.toString()
        //fragBinding.markImage = mark.image //TODO-> Assignment 2
        mark.views++
        fragBinding.viewsText.text = mark.views.toString()
        fragBinding.markText.text = mark.messageText
        fragBinding.ratioBar.max = (mark.goodRatings + mark.poorRatings)
        fragBinding.ratioBar.progress = mark.goodRatings

        fragBinding.backButton.setOnClickListener {
            markStore.update(mark)
            exitMark()
        }

        fragBinding.rateGoodButton.setOnClickListener {
            mark.goodRatings++
            markStore.update(mark)
            exitMark()
        }

        fragBinding.ratePoorButton.setOnClickListener {
            mark.poorRatings++
            markStore.update(mark)
            exitMark()
        }

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MessageViewFragment().apply {
                arguments = Bundle().apply { }
            }
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

    fun exitMark(){
        val manager : FragmentManager = requireFragmentManager()
        val fragment = MessagesFragment()
        val transaction = manager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}