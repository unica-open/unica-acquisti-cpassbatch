/*-
 * ========================LICENSE_START=================================
 * CPASS Batch
 * %%
 * Copyright (C) 2020 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2020 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbatch.dao.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ConnectionManager.
 */
public abstract class ConnectionManager {

	private static Logger log = LogManager.getLogger(ConnectionManager.class);

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public abstract Connection getConnection() throws SQLException;

	/**
	 * Close connection.
	 *
	 * @param conn the conn
	 * @param stmt the stmt
	 * @param rs   the rs
	 */
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conn);
	}

	/**
	 * Close connection.
	 *
	 * @param conn the conn
	 * @param stmt the stmt
	 */
	public static void closeConnection(Connection conn, Statement stmt) {
		closeStatement(stmt);
		closeConnection(conn);
	}

	/**
	 * Close connection.
	 *
	 * @param conn the conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (final SQLException e) {
				log.error("closeConnection", e);
			}
			conn = null;
		}
	}

	/**
	 * Close statement.
	 *
	 * @param stmt the stmt
	 */
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (final SQLException e) {
				log.error("closeStatement", e);
			}
		}
	}

	/**
	 * Close result set.
	 *
	 * @param rs the rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (final SQLException e) {
				log.error("closeResultSet", e);
			}
		}
	}
}
