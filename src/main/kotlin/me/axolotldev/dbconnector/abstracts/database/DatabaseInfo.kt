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

import kotlin.collections.iterator

/**
 * Data class representing the basic information needed to connect to a database.
 *
 * @property uri The hostname or IP address of the database server.
 * @property port The port number of the database server, or null if defaulted.
 * @property username The username used to authenticate to the database, nullable.
 * @property _realPassword The actual password used to authenticate, stored privately.
 * @property database The specific database name to connect to, nullable.
 * @property connectionOption Additional connection options as key-value pairs.
 */
@Suppress("unused")
data class DatabaseInfo(
    var uri: String,
    var port: Int?,
    var username: String?,
    private var _realPassword: String?,
    var database: String?,
    var connectionOption: Map<String, String?>
) {

    /**
     * The password property is writeable but reading it throws an exception.
     * Use [realPassword] to safely retrieve the password instead.
     */
    var password: String?
        get() = throw UnsupportedOperationException("Use realPassword() instead.")
        set(value) {
            _realPassword = value
        }

    /**
     * Returns the actual password used for database authentication.
     *
     * @return The password string, or null if not set.
     */
    fun realPassword(): String? = _realPassword

    /**
     * Returns a string representation of the DatabaseInfo object.
     *
     * @param showPassword Whether to include the real password in the output.
     * @return A string containing all the database info, masking password if requested.
     */
    fun toString(showPassword: Boolean): String {
        return "DatabaseInfo(uri='$uri', port=$port, username='$username', password='" +
                (if (showPassword) realPassword() else "****") + "', database='$database', connectionOption=$connectionOption)"
    }

    override fun toString(): String = toString(false)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DatabaseInfo) return false

        val basic = uri == other.uri &&
                port == other.port &&
                username == other.username &&
                _realPassword == other._realPassword &&
                database == other.database

        val otherMeta = HashMap<String, String?>(other.connectionOption)
        for ((key, value) in connectionOption) {
            if (value != otherMeta[key]) return false
            otherMeta.remove(key)
        }

        if (otherMeta.isNotEmpty()) return false

        return basic
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + (port ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (_realPassword?.hashCode() ?: 0)
        result = 31 * result + (database?.hashCode() ?: 0)
        result = 31 * result + connectionOption.hashCode()
        return result
    }
}
