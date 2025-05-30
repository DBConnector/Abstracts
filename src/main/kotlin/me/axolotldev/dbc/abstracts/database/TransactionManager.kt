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

package me.axolotldev.dbc.abstracts.database

import java.sql.SQLException

/**
 * Interface for managing database transactions.
 *
 * Provides basic transaction control methods: begin, commit, and rollback.
 */
@Suppress("unused")
interface TransactionManager {

    /**
     * Starts a new transaction.
     *
     * @throws SQLException if beginning the transaction fails.
     */
    @Throws(SQLException::class)
    fun begin()

    /**
     * Commits the current transaction, making all changes permanent.
     *
     * @throws SQLException if the commit operation fails.
     */
    @Throws(SQLException::class)
    fun commit()

    /**
     * Rolls back the current transaction, undoing all changes made in the transaction.
     *
     * @throws SQLException if the rollback operation fails.
     */
    @Throws(SQLException::class)
    fun rollback()
}

