package ao.co.euclidesquissembe.proffy.ui.proffys

import android.content.Context
import androidx.lifecycle.*
import ao.co.euclidesquissembe.proffy.data.database.DB
import ao.co.euclidesquissembe.proffy.data.models.Proffy
import ao.co.euclidesquissembe.proffy.data.repository.Repository
import ao.co.euclidesquissembe.proffy.data.response.FilterResponse
import kotlinx.coroutines.launch

class ProffysViewModel(context: Context) : ViewModel() {

    // Criando uma instância do repositório
    private val repository: Repository

    // Observando os dados
    val proffys: LiveData<List<Proffy>>
    val filters: LiveData<FilterResponse>
    val proffysFiltered: LiveData<List<Proffy>>

    init {
        val dao = DB.getInstance(context).dao()
        repository = Repository(dao)
        proffys = repository.proffys
        filters = repository.filters
        proffysFiltered = repository.proffysFiltered
    }

    fun getProffys(page: Int = 1) = viewModelScope.launch {
        // Buscando os dados no repositório
        repository.getAllProffys(page)
    }

    fun getProffysFiltered(matter: String, day: String, time: String, page: Int = 1) = viewModelScope.launch {
        // Buscando os dados no repositório
        repository.getAllProffys(page, matter, day, time)
    }

    fun favorite(favorite: Proffy) = viewModelScope.launch {
        repository.update(favorite)
    }

    fun getFilters() = viewModelScope.launch {
        repository.getFilters()
    }

    class ProffysViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProffysViewModel(context) as T
        }
    }
}