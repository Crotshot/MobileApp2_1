package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.adapters.MarkAdapter
import wit.assignments.mobapp2_1.databinding.FragmentMessagesBinding
import wit.assignments.mobapp2_1.main.MicaAppMain

class MessagesFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentMessagesBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as MicaAppMain
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentMessagesBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_messages)

        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        fragBinding.recyclerView.adapter = MarkAdapter(app.markStore.findAll())

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MessagesFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}