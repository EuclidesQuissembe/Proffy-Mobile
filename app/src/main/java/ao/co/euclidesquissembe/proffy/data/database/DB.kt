package ao.co.euclidesquissembe.proffy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ao.co.euclidesquissembe.proffy.data.DaoInterface
import ao.co.euclidesquissembe.proffy.data.models.Proffy

@Database(entities = [Proffy::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun dao(): DaoInterface

    companion object {
        private var INSTANCE: DB? = null

        fun getInstance(context: Context): DB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DB::class.java,
                    "database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}