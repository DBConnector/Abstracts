/*
*
* Copyright (C) 2025 AxolotlDev and the Abstracts contributors
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*
*/

package me.axolotldev.dbconnector.abstracts.database

import java.lang.AutoCloseable
import java.sql.CallableStatement
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import kotlin.jvm.Throws

/**
 * Represents a basic database connector interface for executing SQL operations.
 *
 * Implementations are responsible for managing the database connection lifecycle
 * and executing SQL queries via statement objects.
 */
@Suppress("unused")
interface Connector : AutoCloseable {

    /**
     * Establishes a connection to the target database.
     *
     * @throws SQLException if the connection fails.
     */
    @Throws(SQLException::class)
    fun connect()

    /**
     * Executes a plain SQL statement.
     *
     * @param query The SQL query to be executed.
     * @return true if the execution produces a ResultSet object; false if it returns an update count or no result.
     * @throws SQLException if the execution fails.
     */
    @Throws(SQLException::class)
    fun execute(query: String): Boolean

    /**
     * Prepares and returns a SQL [PreparedStatement] for parameterized queries.
     *
     * @param query The SQL query to prepare.
     * @return The prepared statement object.
     * @throws SQLException if preparation fails.
     */
    @Throws(SQLException::class)
    fun preparedExecute(query: String): PreparedStatement

    /**
     * Prepares a callable statement for stored procedure calls.
     *
     * @param query The SQL query or procedure call.
     * @return The callable statement object.
     * @throws SQLException if preparation fails.
     */
    @Throws(SQLException::class)
    fun call(query: String): CallableStatement

    /**
     * Returns the raw [Connection] instance, or `null` if not connected.
     *
     * @return The JDBC connection.
     */
    fun getRaw(): Connection?

    /**
     * Checks whether the connection is active and open.
     *
     * @return `true` if connected and not closed, otherwise `false`.
     */
    fun isConnected(): Boolean = try {
        !(getRaw()?.isClosed ?: true)
    } catch (_: SQLException) {
        false
    }

    /**
     * Returns the transaction manager instance responsible for handling
     * commit and rollback operations.
     *
     * @return The [TransactionManager] object.
     */
    fun getTransactionManager(): TransactionManager

    /**
     * Closes the connector and releases the underlying database connection.
     *
     * @throws SQLException if an error occurs while closing.
     */
    @Throws(SQLException::class)
    override fun close()
}
