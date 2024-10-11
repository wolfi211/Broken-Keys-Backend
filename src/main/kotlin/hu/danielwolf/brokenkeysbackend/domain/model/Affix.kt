package hu.danielwolf.brokenkeysbackend.domain.model

data class Affix(
  var id: Long? = null,
  var name: String? = null,
  var description: String? = null,
  var icon: String? = null,
  var iconUrl: String? = null,
  var wowheadUrl: String? = null,
) {
  companion object {
    fun convertFromDto(dto: Map<*, *>): Affix {
      return Affix().apply {
        id = dto["id"]?.toString()?.toLongOrNull()
        name = dto["name"]?.toString()
        description = dto["description"].toString()
        icon = dto["icon"].toString()
        wowheadUrl = dto["wowhead_url"].toString()
        iconUrl = "https://wow.zamimg.com/images/wow/icons/large/${icon}.jpg"
      }
    }

    fun convertFromDtoArray(dto:List<Map<*,*>>): List<Affix> {
      return dto.map { convertFromDto(it) }
    }
  }
}