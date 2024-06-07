package br.com.webscraping.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Scraping(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val cover: String,
    val title: String,
    val author: String?,
    val category: String?,
    val gender: String?
) {
    constructor() : this(null, "", "", null, null, null)
}
