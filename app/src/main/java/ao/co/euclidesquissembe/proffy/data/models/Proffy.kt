package ao.co.euclidesquissembe.proffy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "proffys")
data class Proffy (
    @PrimaryKey
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    val photo: String,
    val phone: String,
    val bio: String,
    var favorite: Boolean,
    val price: Double,
    val subject: String
)