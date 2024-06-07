package br.com.webscraping.services

import br.com.webscraping.config.WebScraping
import br.com.webscraping.model.Scraping
import br.com.webscraping.repository.ScrapingRepository
import org.springframework.stereotype.Service

private const val URL = "https://pnld.moderna.com.br/categoria-literatura/ensinomedio/"

@Service
class ScrapingServices(
    private val scraping: WebScraping,
    private val scrapingRepository: ScrapingRepository
) {
    fun searchAllInformations(): MutableList<Scraping> {
        return scraping.fetchNewData(URL)
    }

    fun saveJsonInformations(data: MutableList<Scraping>): MutableList<Scraping> {
        val getAllExisting = getJsonInformations()

        val newData = data.filter { newDataItem ->
            getAllExisting.none { existingDataItem ->
                areScrapingEqual(newDataItem, existingDataItem)
            }
        }

        if (newData.isNotEmpty()) {
            scrapingRepository.deleteAll()
            scrapingRepository.saveAll(newData)
        }
        return getJsonInformations()
    }

    fun getJsonInformations(): MutableList<Scraping> {
        return scrapingRepository.findAll()
    }

    private fun areScrapingEqual(newScraping: Scraping, existingScraping: Scraping): Boolean {
        return newScraping.cover == existingScraping.cover &&
                newScraping.title == existingScraping.title &&
                newScraping.author == existingScraping.author &&
                newScraping.category == existingScraping.category &&
                newScraping.gender == existingScraping.gender
    }
}