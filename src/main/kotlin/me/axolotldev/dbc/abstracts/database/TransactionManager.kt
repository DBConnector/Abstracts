package me.axolotldev.dbc.abstracts.database

import java.sql.SQLException

@Suppress("unused")
interface TransactionManager {

    @Throws(SQLException::class)
    fun begin()

    @Throws(SQLException::class)
    fun commit()

    @Throws(SQLException::class)
    fun rollback()
}
