package ao.co.euclidesquissembe.proffy.ui.proffys

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ao.co.euclidesquissembe.proffy.R
import ao.co.euclidesquissembe.proffy.adapters.Adapter
import kotlinx.android.synthetic.main.fragment_proffys.view.*
import kotlinx.android.synthetic.main.header_item_proffys.*
import kotlinx.android.synthetic.main.header_item_proffys.view.*
import kotlinx.android.synthetic.main.layout_timer.*
import kotlinx.android.synthetic.main.main_toolbar.view.*

class ProffysFragment : Fragment() {

    private var hour: String = ""
    private var minute: String = ""
    private var dayValue: String = ""
    private var matterValue: String = ""

    private val viewModel: ProffysViewModel by lazy {
        ViewModelProvider(
            this,
            ProffysViewModel.ProffysViewModelFactory(requireContext())
        ).get(ProffysViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_proffys, container, false)

        val adapter = Adapter(requireActivity()) { proffy ->
            viewModel.favorite(proffy)
        }

        view.recyclerProffys.adapter = adapter

        viewModel.proffys.observe(viewLifecycleOwner, Observer { proffys ->
            proffys?.let {
                adapter.setItems(it)
            }
        })

        viewModel.proffysFiltered.observe(viewLifecycleOwner, Observer {filtered ->
            filtered?.let {
                adapter.setItems(it)
            }
        })

        viewModel.getProffys()

        val itemsDay = arrayListOf<String>()
        val itemsMatter = arrayListOf<String>()

        itemsDay.add("")
        itemsMatter.add("")

        viewModel.filters.observe(viewLifecycleOwner, Observer {filters ->
            for (day in filters.days) {
                itemsDay.add(day.name)
            }

            for (matter in filters.matters) {
                itemsMatter.add(matter.name)
            }
        })

        viewModel.getFilters()

        view.showFilter.setOnClickListener {
            view.layoutFilter.visibility =
                if (view.layoutFilter.visibility != View.VISIBLE) View.VISIBLE else View.GONE
        }

        val adapterItemsWeek = ArrayAdapter(requireContext(), R.layout.list_item, itemsDay)

        val week = view.weekLayout.editText as? AutoCompleteTextView
        week?.setAdapter(adapterItemsWeek)
        week?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            dayValue = itemsDay[position]
            filter()
        }

        val adapterItemsMatter = ArrayAdapter(requireContext(), R.layout.list_item, itemsMatter)

        val matter = view.matterLayout.editText as? AutoCompleteTextView
        matter?.setAdapter(adapterItemsMatter)
        matter?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                matterValue = itemsMatter[position]
                filter()
            }

        view.time.setOnClickListener {
            customDialogTimer()
        }

        view.back.setOnClickListener {
            requireActivity().finish()
        }

        return view
    }

    private fun filter() {

        val time = if (hour.isEmpty() && minute.isEmpty()) "" else "$hour:$minute"

        viewModel.getProffysFiltered(matterValue, dayValue, time)
    }

    private fun customDialogTimer() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.layout_timer)
        dialog.setTitle("Escolha o horário")

        dialog.setOnDismissListener {

            time.text = "$hour:$minute"
            filter()
        }

        dialog.timer.setOnTimeChangedListener { view, hourOfDay, minute ->
            hour = hourOfDay.toString()
            this.minute = minute.toString()
        }

        dialog.show()
    }
}