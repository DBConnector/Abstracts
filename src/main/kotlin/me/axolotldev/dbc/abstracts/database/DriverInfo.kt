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

/**
 * Interface representing a database driver information and URI generation.
 */
@Suppress("unused")
interface DriverInfo {

    /**
     * The name of the database driver.
     */
    val name: String

    /**
     * The driver connection address prefix or base uri.
     */
    val driverAddress: String

    /**
     * Generates the full database connection URI based on the provided [DatabaseInfo].
     *
     * @param info The database connection information.
     * @return The constructed database connection URI as a String.
     */
    fun generateURI(info: DatabaseInfo): String

    /**
     * Validates whether the given uri string matches the expected format for this driver.
     *
     * @param uri The uri string to validate.
     * @return True if the uri is valid for this driver, false otherwise.
     */
    fun isValidURI(uri: String): Boolean
}
