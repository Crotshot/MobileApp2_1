package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.FragmentMapBinding
import wit.assignments.mobapp2_1.main.MicaAppMain

class MapFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentMapBinding? = null
    private val fragBinding get() = _fragBinding!!

    //Delete later, test var
    var presses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MapFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}