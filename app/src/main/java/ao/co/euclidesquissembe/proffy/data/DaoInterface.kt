package ao.co.euclidesquissembe.proffy.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import ao.co.euclidesquissembe.proffy.data.models.Proffy

@Dao
interface DaoInterface {

    @Query("SELECT * FROM proffys")
    fun getAll(): LiveData<List<Proffy>>

    @Query("SELECT * FROM proffys WHERE favorite = :favorite")
    fun getAllFavorites(favorite: Boolean = true): LiveData<List<Proffy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(proffy: List<Proffy>)

    @Update
    suspend fun update(proffy: Proffy)

    @Query("DELETE FROM proffys")
    suspend fun removeAll()
}