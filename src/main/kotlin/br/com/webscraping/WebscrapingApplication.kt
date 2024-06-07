package br.com.webscraping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WebscrapingApplication

fun main(args: Array<String>) {
    runApplication<WebscrapingApplication>(*args)
}
