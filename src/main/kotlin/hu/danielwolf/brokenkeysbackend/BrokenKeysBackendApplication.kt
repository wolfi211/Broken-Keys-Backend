package hu.danielwolf.brokenkeysbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BrokenKeysBackendApplication

fun main(args: Array<String>) {
  runApplication<BrokenKeysBackendApplication>(*args)
}
