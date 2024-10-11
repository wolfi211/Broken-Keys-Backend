package hu.danielwolf.brokenkeysbackend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Config(
  @Value("\${raiderio.base-url}")
  private val raiderioBaseUrl: String,
  private val webClientBuilder: WebClient.Builder
) {

  @Bean
  fun raiderioWebClient() = webClientBuilder.baseUrl(raiderioBaseUrl).build()
}