package ao.co.euclidesquissembe.proffy.data.response

import ao.co.euclidesquissembe.proffy.data.models.Matter
import ao.co.euclidesquissembe.proffy.data.models.Day

data class FilterResponse (
    val matters: List<Matter>,
    val days: List<Day>
)