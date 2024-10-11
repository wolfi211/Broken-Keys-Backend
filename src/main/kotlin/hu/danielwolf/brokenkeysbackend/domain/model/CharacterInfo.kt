package hu.danielwolf.brokenkeysbackend.domain.model

import java.time.ZonedDateTime
import kotlin.math.roundToInt

data class CharacterInfo(
  var name: String? = null,
  var region: String? = null,
  var realm: String? = null,
  var race: String? = null,
  var gender: String? = null,
  var faction: String? = null,
  var portraitUrl: String? = null,
  var charClass: String? = null,
  var specialization: String? = null,
  var role: String? = null,
  var score: Int? = null,
  var achievementPoints: Long = 0,
  var lastUpdatedAt: ZonedDateTime? = null,
  var itemLevel: Double? = null,
  var items: Map<String, Item> = emptyMap(),
  var raiderIoProfileUrl: String? = null,
  var recentRuns: List<MythicPlusRun> = emptyList(),
  var bestRuns: List<MythicPlusRun> = emptyList(),
  var highestLevelRuns: List<MythicPlusRun> = emptyList(),
  var weeklyHighestLevelRuns: List<MythicPlusRun> = emptyList(),
  var previousWeeklyHighestLevelRuns: List<MythicPlusRun> = emptyList(),
) {

  companion object {
    fun convertFromDto(dto: Map<*, *>): CharacterInfo {
      return CharacterInfo().apply {
        name = dto["name"]?.toString()
        race = dto["race"]?.toString()
        charClass = dto["class"]?.toString()
        specialization = dto["active_spec_name"]?.toString()
        role = dto["active_spec_role"]?.toString()
        gender = dto["gender"]?.toString()
        faction = dto["faction"]?.toString()
        achievementPoints = dto["achievement_points"]?.toString()?.toLong()?: 0
        portraitUrl = dto["thumbnail_url"]?.toString()
        region = dto["region"]?.toString()
        realm = dto["realm"]?.toString()
        lastUpdatedAt = ZonedDateTime.parse(dto["last_crawled_at"]?.toString())
        raiderIoProfileUrl = dto["profile_url"]?.toString()
        itemLevel = (dto["gear"] as Map<*, *>)["item_level_equipped"]?.toString()?.toDouble()
        items = Item.convertFromDtoArray((dto["gear"] as Map<*, *>)["items"] as Map<*, *>)
        recentRuns = MythicPlusRun.convertFromDtoArray(dto["mythic_plus_recent_runs"] as List<Map<*, *>>)
        bestRuns = MythicPlusRun.convertFromDtoArray(dto["mythic_plus_best_runs"] as List<Map<*, *>>)
        highestLevelRuns = MythicPlusRun.convertFromDtoArray(dto["mythic_plus_highest_level_runs"] as List<Map<*, *>>)
        weeklyHighestLevelRuns = MythicPlusRun.convertFromDtoArray(dto["mythic_plus_weekly_highest_level_runs"] as List<Map<*, *>>)
        previousWeeklyHighestLevelRuns = MythicPlusRun.convertFromDtoArray(dto["mythic_plus_previous_weekly_highest_level_runs"] as List<Map<*, *>>)
        score = bestRuns.sumOf { it.score?:0.0 }.roundToInt()
      }
    }
  }
}