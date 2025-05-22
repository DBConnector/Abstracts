package me.axolotldev.dbc.abstracts.database

@Suppress("unused")
interface DriverInfo {
    val name: String
    val defaultPort: Int
    val jdbcPrefix: String
    fun isValidUrl(url: String): Boolean
}
