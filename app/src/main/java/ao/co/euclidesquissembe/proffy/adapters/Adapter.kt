package ao.co.euclidesquissembe.proffy.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import ao.co.euclidesquissembe.proffy.R
import ao.co.euclidesquissembe.proffy.data.models.Proffy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_item.view.*

class Adapter(
    private val activity: Activity,
    private val onClick: ((proffy: Proffy) -> Unit)
) :
    RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    private var items: List<Proffy> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return AdapterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    fun setItems(proffys: List<Proffy>) {
        this.items = proffys
        notifyDataSetChanged()
    }

    inner class AdapterViewHolder(
        private val item: View, private val onClick: ((proffy: Proffy) -> Unit)
    ) : RecyclerView.ViewHolder(item) {

        fun bind(proffy: Proffy) {

            Picasso.get().load(proffy.photo).into(item.imageUser)
            item.fullName.text = proffy.fullName
            item.bio.text = HtmlCompat.fromHtml(proffy.bio, HtmlCompat.FROM_HTML_MODE_COMPACT)
            item.subject.text = proffy.subject
            item.price.text = activity.getString(R.string.price_number, proffy.price.toString())

            item.imageView.setImageResource(changeIcon(proffy.favorite))
            item.imageView.setOnClickListener {
                proffy.favorite = !proffy.favorite
                item.imageView.setImageResource(changeIcon(proffy.favorite))
                onClick.invoke(proffy)
            }
        }

        private fun changeIcon(bool: Boolean): Int {
            return if (bool) R.drawable.ic_botao_unfav else R.drawable.ic_botao_fav
        }
    }
}