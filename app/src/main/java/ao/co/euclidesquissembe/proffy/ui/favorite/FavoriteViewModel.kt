package ao.co.euclidesquissembe.proffy.ui.favorite

import android.content.Context
import androidx.lifecycle.*
import ao.co.euclidesquissembe.proffy.data.database.DB
import ao.co.euclidesquissembe.proffy.data.models.Proffy
import ao.co.euclidesquissembe.proffy.data.repository.Repository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val context: Context) : ViewModel() {

    private val repository: Repository
    val favorites: LiveData<List<Proffy>>

    init {
        val dao = DB.getInstance(context).dao()
        repository = Repository(dao)
        favorites = repository.favorites
    }

    fun favorite(favorite: Proffy) = viewModelScope.launch {
        repository.update(favorite)
    }

    class FavoriteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FavoriteViewModel(context) as T
        }
    }
}