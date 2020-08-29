package ao.co.euclidesquissembe.proffy.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ao.co.euclidesquissembe.proffy.R
import ao.co.euclidesquissembe.proffy.adapters.Adapter
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(
            this,
            FavoriteViewModel.FavoriteViewModelFactory(requireContext())
        ).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val adapter = Adapter(requireActivity()) { favorite ->
            viewModel.favorite(favorite)
        }

        view.recyclerFavorites.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner, Observer { favorites ->
            favorites?.let {
                adapter.setItems(proffys = favorites)
            }
        })

        return view
    }
}