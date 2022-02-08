package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.FragmentMessageViewBinding
import wit.assignments.mobapp2_1.main.MicaAppMain

class MessageViewFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentMessageViewBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MicaAppMain
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentMessageViewBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_message)

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
}