package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.FragmentPostBinding
import wit.assignments.mobapp2_1.main.MicaAppMain

class PostFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentPostBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MicaAppMain
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentPostBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_post)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}