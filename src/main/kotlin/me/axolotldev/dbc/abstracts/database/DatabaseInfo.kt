package me.axolotldev.dbc.abstracts.database

@Suppress("unused")
data class DatabaseInfo(
    var url: String,
    var port: Int?,
    var username: String,
    private var _password: String,
    var database: String,
    var connectionOption: Map<String, String?>
) {

    var password: String
        get() = throw UnsupportedOperationException("Use realPassword() instead.")
        set(value) {
            _password = value
        }

    fun realPassword(): String = _password

    fun toString(showPassword: Boolean): String {
        return "DatabaseInfo(url='$url', port=$port, username='$username', password='" + (if (showPassword) password else "****") + "', database='$database', connectionOption=$connectionOption)"
    }

    override fun toString(): String {
        return toString(false)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DatabaseInfo) return false

        val basic = url == other.url
                && port == other.port
                && username == other.username
                && _password == other._password
                && database == other.database

        val otherMeta = HashMap<String, String?>(other.connectionOption)

        for (entry in connectionOption) {
            if (entry.value != otherMeta[entry.key]) {
                return false
            }
            otherMeta.remove(entry.key)
        }

        if (otherMeta.isNotEmpty()) return false

        return basic
    }


    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + (port ?: 0)
        result = 31 * result + username.hashCode()
        result = 31 * result + _password.hashCode()
        result = 31 * result + database.hashCode()
        result = 31 * result + connectionOption.hashCode()
        return result
    }


}
