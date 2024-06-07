package br.com.webscraping.services

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScrapingScheduler(
    private val scrapingService: ScrapingServices
) {
    @Scheduled(fixedRate = 60000)
    fun performScraping() {
        try {
            val newData = scrapingService.searchAllInformations()
            scrapingService.saveJsonInformations(newData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}