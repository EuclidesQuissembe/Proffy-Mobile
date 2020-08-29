package ao.co.euclidesquissembe.proffy.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ao.co.euclidesquissembe.proffy.data.Api.service
import ao.co.euclidesquissembe.proffy.data.DaoInterface
import ao.co.euclidesquissembe.proffy.data.models.Proffy
import ao.co.euclidesquissembe.proffy.data.response.FilterResponse

class Repository(private val dao: DaoInterface) {

    private val TAG = "ProffysRepository"

    val proffys: LiveData<List<Proffy>> = dao.getAll()
    val favorites: LiveData<List<Proffy>> = dao.getAllFavorites()
    val filters: MutableLiveData<FilterResponse> = MutableLiveData()
    val proffysFiltered: MutableLiveData<List<Proffy>> = MutableLiveData()

    suspend fun getAllProffys(page: Int) {
        try {
            // Buscando os dados no servidor
            val allProffys = service.getAllProffys(page)

            if (allProffys.proffys.isNotEmpty()) {
                // Apagando os dados antigos na tabela
                deleteAll()

                // Salvando os proffys localmente
                insertAll(allProffys.proffys)
            }
        } catch (cause: Throwable) {
            Log.e(TAG, cause.message, cause)
        }
    }

    suspend fun getAllProffys(page: Int, matter: String, day: String, time: String) {
        try {
            // Buscando os dados no servidor
            val allProffys = service.getAllProffys(page, matter, day, time)

            proffysFiltered.value = allProffys.proffys
        } catch (cause: Throwable) {
            Log.e(TAG, cause.message, cause)
        }
    }

    suspend fun getFilters() {
        try {
            // Buscando os dados de filtros no servidor
            val filters = service.getFilters()

            this.filters.value = filters
        } catch (cause: Throwable) {
            Log.e(TAG, cause.message, cause)
        }
    }

    private suspend fun insertAll(proffys: List<Proffy>) {
        dao.insertAll(proffys)
    }

    suspend fun update(proffy: Proffy) {
        dao.update(proffy)
    }

    private suspend fun deleteAll() {
        dao.removeAll()
    }
}