package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.FragmentMapBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkModel

class MapFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentMapBinding? = null
    private val fragBinding get() = _fragBinding!!

    //Delete later, test var
    var buttonPresses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MicaAppMain
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentMapBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_map)

        setButtonListener(fragBinding)
        return root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
    }

    fun setButtonListener(layout: FragmentMapBinding) {
        layout.markMakerButton.setOnClickListener {
            buttonPresses++
            Toast.makeText(
                context, "Button has been pressed $buttonPresses times!",
                Toast.LENGTH_SHORT
            ).show()
            app.markStore.create(
                MarkModel(
                    messageText = "Press $buttonPresses",
                    userName = "Bob",
                    views = 342432,
                    goodRatings = 342,
                    poorRatings = 1
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_map, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MapFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}