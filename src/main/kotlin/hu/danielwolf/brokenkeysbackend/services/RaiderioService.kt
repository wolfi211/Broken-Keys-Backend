package hu.danielwolf.brokenkeysbackend.services

import hu.danielwolf.brokenkeysbackend.domain.model.CharacterInfo
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException

@Service
class RaiderioService(
  @Value("\${raiderio.base-url}")
  private val raiderioBaseUrl: String,
  @Value("\${raiderio.current-season}")
  private val currentSeason: String,
  webClientBuilder: WebClient.Builder,
) {

  private val webClient: WebClient = webClientBuilder
    .baseUrl(raiderioBaseUrl)
    .exchangeStrategies(ExchangeStrategies.builder()
      .codecs{ codecs ->
        codecs
          .defaultCodecs()
          .maxInMemorySize(10 * 1024 * 1024)
      }
      .build())
    .build()

  fun getCharacterInfo(region: String, name: String, realm: String, options: List<String>): CharacterInfo {
    val characterDto = webClient.get().uri { uriBuilder ->
      uriBuilder
        .path("/characters/profile")
        .queryParam("region", region)
        .queryParam("realm", realm)
        .queryParam("name", name)
        .queryParam("fields", options.joinToString(separator = ","))
        .build()
    }
      .retrieve()
      .bodyToMono(Map::class.java)
      .block()
    return characterDto?.let { CharacterInfo.convertFromDto(it) }
      ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "character.not-found")
  }

  fun getRunDetails(id: Long): Map<*, *>? =
    webClient.get().uri { uriBuilder ->
        uriBuilder
          .path("/mythic-plus/run-details")
          .queryParam("season", currentSeason)
          .queryParam("id", id)
          .build()
      }
      .retrieve()
      .bodyToMono(Map::class.java)
      .block()

  fun getMultipleRunDetails(ids: List<Long?>): List<Map<*, *>?> =
    ids.map { id -> id?.let { getRunDetails(it) } }
}

//https://raider.io/api/search?term=Fejy