package wit.assignments.mobapp2_1.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import timber.log.Timber
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.adapters.MarkAdapter
import wit.assignments.mobapp2_1.databinding.FragmentMessagesBinding
import wit.assignments.mobapp2_1.main.MicaAppMain
import wit.assignments.mobapp2_1.models.MarkJSONStore
import wit.assignments.mobapp2_1.models.MarkStore

class MessagesFragment : Fragment() {

    lateinit var app: MicaAppMain
    private var _fragBinding: FragmentMessagesBinding? = null
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
        _fragBinding = FragmentMessagesBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_messages)

        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        fragBinding.recyclerView.adapter = MarkAdapter(app.markStore, "", this)

        fragBinding.searchBar.setOnSearchClickListener {
            Timber.i("Editing Search Bar")
        }

        val t = this
        fragBinding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.i("Confirmed Searching : ${fragBinding.searchBar.query}")
                fragBinding.recyclerView.adapter = null
                fragBinding.recyclerView.adapter = MarkAdapter(app.markStore, fragBinding.searchBar.query.toString(), t)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.i("Text Changed Searching : ${fragBinding.searchBar.query}")
                fragBinding.recyclerView.adapter = null
                fragBinding.recyclerView.adapter = MarkAdapter(app.markStore, fragBinding.searchBar.query.toString(), t)
                return true
            }
        })

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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_messages, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }


    fun onCardClicked(id : Long, markStore: MarkStore){
        val manager : FragmentManager = requireFragmentManager()
        val fragment = MessageViewFragment()
        val transaction = manager.beginTransaction()
        transaction.setReorderingAllowed(true)

        val bundle = Bundle()
        bundle.putSerializable("markStore", markStore as MarkJSONStore)
        bundle.putLong("id", id)

        fragment.arguments = bundle
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}