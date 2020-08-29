package ao.co.euclidesquissembe.proffy.data.response

import ao.co.euclidesquissembe.proffy.data.models.Proffy

data class ProffysResponse (
    val proffys: List<Proffy>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int,
    val message: String?,
    val status: Int
)