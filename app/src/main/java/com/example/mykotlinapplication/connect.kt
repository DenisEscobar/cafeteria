package com.example.mykotlinapplication

import java.sql.DriverManager
import java.sql.*
import java.util.Properties

class connect {

    object MySQLDatabaseExampleKotlin {
        var text=null
        internal var conn: Connection? = null
        internal var url ="jdbc:mysql://192.168.2.22:3306/"
        internal var username = "root"
        internal var password = "1234"

        fun conne() {
            getConnection()
            executeMySQLQuery()
        }

        fun executeMySQLQuery() {
            var stmt: Statement? = null
            var resultset: ResultSet? = null

            try {
                stmt = conn!!.createStatement()
                resultset = stmt!!.executeQuery("SHOW DATABASES;")

                if (stmt.execute("SHOW DATABASES;")) {
                    resultset = stmt.resultSet
                }

                while (resultset!!.next()) {
                    println(resultset.getString("Database"))
                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            } finally {
                if (resultset != null) {
                    try {
                        resultset.close()
                    } catch (sqlEx: SQLException) {
                    }
                    resultset = null
                }
                if (stmt != null) {
                    try {
                        stmt.close()
                    } catch (sqlEx: SQLException) { }
                    stmt = null
                }
                if (conn != null) {
                    try {
                        conn!!.close()
                    } catch (sqlEx: SQLException) { }
                    conn = null
                }
            }
        }

        fun getConnection() {
            val connectionProps = Properties()
            connectionProps.put("user", username)
            connectionProps.put("password", password)
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance()
                conn = DriverManager.getConnection(url, connectionProps)
            } catch (ex: SQLException) {
                ex.printStackTrace()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}