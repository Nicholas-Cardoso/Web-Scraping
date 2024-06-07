package br.com.webscraping.controller

import br.com.webscraping.model.Scraping
import br.com.webscraping.services.ScrapingServices
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/web-scraping")
class ScrapingController(
    private val webServices: ScrapingServices
) {
    @GetMapping
    fun getAllInformations(): MutableList<Scraping> {
        return webServices.getJsonInformations()
    }
}