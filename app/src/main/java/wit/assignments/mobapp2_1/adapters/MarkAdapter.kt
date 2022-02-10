package wit.assignments.mobapp2_1.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import wit.assignments.mobapp2_1.databinding.CardMarkBinding
import wit.assignments.mobapp2_1.models.MarkModel

class MarkAdapter constructor(private var marks: List<MarkModel>)
    : RecyclerView.Adapter<MarkAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardMarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val mark = marks[holder.adapterPosition]
        holder.bind(mark)
    }

    override fun getItemCount(): Int = marks.size

    inner class MainHolder(val binding : CardMarkBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mark: MarkModel) {
            binding.messageCardText.text = mark.messageText
            binding.messageUsername.text = mark.userName
            binding.messageGoodCount.text = mark.goodRatings.toString()
            binding.messagePoorCount.text = mark.poorRatings.toString()
            binding.messageViews.text = mark.views.toString()

            binding.pressableCard.setOnClickListener{
                Timber.i("Card Pressed")
                //TODO Open message view from here with card information
            }
        }
    }
}