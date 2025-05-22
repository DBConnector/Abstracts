package me.axolotldev.dbc.abstracts.database

import java.lang.AutoCloseable
import java.sql.CallableStatement
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement
import kotlin.jvm.Throws

@Suppress("unused")
interface Connector: AutoCloseable {

    @Throws(SQLException::class)
    fun connect()

    @Throws(SQLException::class)
    fun execute(query: String): Statement

    @Throws(SQLException::class)
    fun preparedExecute(query: String): PreparedStatement

    @Throws(SQLException::class)
    fun call(query: String): CallableStatement

    fun getRaw(): Connection?

    fun isConnected(): Boolean {
        return try {
            getRaw()?.let { !it.isClosed } ?: false
        } catch (ex: SQLException) {
            false
        }
    }

    fun getTransactionManager(): TransactionManager

    @Throws(SQLException::class)
    override fun close()

}