package hu.danielwolf.brokenkeysbackend.domain.model

data class Item(
  var id: Long? = null,
  var itemLevel: Int? = null,
  var icon: String? = null,
  var iconUrl: String? = null,
  var name: String? = null,
  var quality: Short? = null,
  var isLegendary: Boolean = false,
  var tier: Short? = null,
  var gems: List<Long?> = emptyList(),
  var bonuses: List<Long?> = emptyList()
) {
  companion object {
    fun convertFromDto(dto: Map<*,*>): Item {
      return Item().apply {
        id = dto["item_id"]?.toString()?.toLong()
        itemLevel = dto["item_level"]?.toString()?.toInt()
        icon = dto["icon"]?.toString()
        name = dto["name"]?.toString()
        quality = dto["item_quality"]?.toString()?.toShort()
        isLegendary = dto["is_legendary"]?.toString()?.toBoolean()?: false
        tier = dto["tier"]?.toString()?.toShortOrNull()
        gems = (dto["gems"] as List<Int>).map { it.toLong() }
        bonuses = (dto["bonuses"] as List<Int>).map { it.toLong() }
        iconUrl = "https://wow.zamimg.com/images/wow/icons/large/${icon}.jpg"
      }
    }

    fun convertFromDtoArray(dto: Map<*,*>): Map<String, Item> {
      var items = mutableMapOf<String, Item>()

      dto.forEach { items[it.key as String] = convertFromDto(it.value as Map<*,*>) }

      return items
    }
  }
}