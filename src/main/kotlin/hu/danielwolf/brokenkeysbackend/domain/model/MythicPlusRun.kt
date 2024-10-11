package hu.danielwolf.brokenkeysbackend.domain.model

import java.time.ZonedDateTime

data class MythicPlusRun(
  var id: Long? = null,
  var dungeon: String? = null,
  var shortName: String? = null,
  var mythicLevel: Short? = null,
  var completedAt: ZonedDateTime? = null,
  var clearTimeMs: Long? = null,
  var parTimeMs: Long? = null,
  var keystoneUpgrades: Short? = null,
  var imageUrl: String? = null,
  var score: Double? = null,
  var affixes: List<Affix> = emptyList(),
  var raiderIoUrl: String? = null
) {
  companion object {
    fun convertFromDto(dto: Map<*, *>): MythicPlusRun {
      return MythicPlusRun().apply {
        id = getRunIdFromUrl(dto["url"].toString())
        dungeon = dto["dungeon"].toString()
        shortName = dto["short_name"].toString()
        mythicLevel = dto["mythic_level"].toString().toShortOrNull()
        completedAt = ZonedDateTime.parse(dto["completed_at"]?.toString())
        clearTimeMs = dto["clear_time_ms"].toString().toLongOrNull()
        parTimeMs = dto["par_time_ms"].toString().toLongOrNull()
        keystoneUpgrades = dto["num_keystone_upgrades"].toString().toShortOrNull()
        imageUrl = dto["background_image_url"].toString()
        score = dto["score"].toString().toDoubleOrNull()
        affixes = Affix.convertFromDtoArray(dto["affixes"] as List<Map<*, *>>)
        raiderIoUrl = dto["url"].toString()
      }
    }

    fun convertFromDtoArray(dto: List<Map<*,*>>): List<MythicPlusRun> {
      return dto.map { convertFromDto(it) }
    }

    private fun getRunIdFromUrl(url: String): Long? =
      url.split('/').last().split('-').first().toLongOrNull()
  }
}