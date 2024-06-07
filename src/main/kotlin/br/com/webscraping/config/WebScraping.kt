package br.com.webscraping.config

import br.com.webscraping.model.Scraping
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.io.IOException
import java.text.Normalizer
import java.util.regex.Pattern

@Component
class WebScraping {
    fun fetchNewData(url: String): MutableList<Scraping> {
        val listInformations = mutableListOf<Scraping>()

        try {
            val doc = Jsoup.connect(url).get()

            val elements = doc.select("h3.book-title")
            for (i in elements.indices) {
                val covers = getAllCovers(url)[i]

                val elementsAutor = elements[i].parent()?.select("h6:contains(Autor)")?.text()
                val author = elementsAutor?.substringAfter("Autor: ")?.trim()

                val elementsCategory = elements[i].parent()?.select("h6:contains(Categoria)")?.text()
                val category = elementsCategory?.substringAfter("Categoria: ")?.trim()

                val elementsGender = elements[i].parent()?.select("h6:contains(Gênero literário)")?.text()
                val gender = elementsGender?.substringAfter("Gênero literário: ")?.trim()

                val scraping = Scraping(
                    cover = covers,
                    title = elements[i].text(),
                    author = author,
                    category = category,
                    gender = gender
                )
                listInformations.add(scraping)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return listInformations
    }

    fun getAllCovers(oldUrl: String): MutableList<String> {
        val doc = Jsoup.connect(oldUrl).get()
        val links = doc.select("div.placeholderbook-1 a")

        val listDescriptions = mutableListOf<String>()
        for (link in links) {
            val descriptionsUrls = link.attr("href")
            val noAccents = removeAccents(descriptionsUrls).replace("\"", "")

            listDescriptions.add(noAccents)
        }

        val listCovers = mutableListOf<String>()
        for (list in listDescriptions) {
            val newDoc = Jsoup.connect(list).get()
            val newLinks = newDoc.select("img")

            val covers = newLinks.attr("data-src")
            val noAccents = removeAccents(covers).replace("\"", "")

            listCovers.add(noAccents)
        }

        return listCovers
    }

    private fun removeAccents(url: String): String {
        val normalizedUrl = Normalizer.normalize(url, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(normalizedUrl).replaceAll("")
    }
}