package hu.danielwolf.brokenkeysbackend.api.raiderio

import hu.danielwolf.brokenkeysbackend.services.RaiderioService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/raiderio")
class RaiderioController(
  private val raiderioService: RaiderioService
) {
  @GetMapping("/character")
  fun getCharacter() = raiderioService.getCharacterInfo(
    "eu",
    "Mónica",
    "Ragnaros",
    listOf(
      "gear",
      "mythic_plus_recent_runs",
      "mythic_plus_best_runs",
      "mythic_plus_alternate_runs:all",
      "mythic_plus_highest_level_runs",
      "mythic_plus_weekly_highest_level_runs",
      "mythic_plus_previous_weekly_highest_level_runs"
    )
  )
}