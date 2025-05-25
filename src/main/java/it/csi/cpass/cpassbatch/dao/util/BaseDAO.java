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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class BaseDAO.
 *
 * @param <T> the generic type
 */
public abstract class BaseDAO<T> implements BaseDAOI<T> {

	private final Logger log = LogManager.getLogger(this.getClass());
	protected ResultSetExtractor<T> resultSetExtractor = null;
	protected StatementMapper statementMapper = new EmptyStatementMapper();

	/**
	 * Componi query.
	 *
	 * @return the string
	 */
	public abstract String componiQuery();

	/**
	 * Sets the result set extractor.
	 *
	 * @param resultSetExtractor the new result set extractor
	 */
	public void setResultSetExtractor(ResultSetExtractor<T> resultSetExtractor) {
		this.resultSetExtractor = resultSetExtractor;
	}

	/**
	 * Sets the statement mapper.
	 *
	 * @param statementMapper the new statement mapper
	 */
	public void setStatementMapper(StatementMapper statementMapper) {
		this.statementMapper = statementMapper;
	}

	/**
	 * Sets the statement params.
	 *
	 * @param params the new statement params
	 */
	public void setStatementParams(Object... params) {
		this.statementMapper = new GenericObjectArrayStatementMapper(params);
	}

	@Override
	public T executeQuery() throws ClassNotFoundException, IOException, SQLException {
		final String METHOD_NAME = "executeQuery";
		log.info(METHOD_NAME, "dao: " + super.getClass().getSimpleName());

		T returnObject = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManagerFactory.getInstance().getConnection();
			final String sql = componiQuery();
			stmt = conn.prepareStatement(sql);
			log.info(METHOD_NAME, "map statement params...");
			statementMapper.mapStatementParameters(stmt);
			log.info(METHOD_NAME, "start execute query: " + sql);
			rs = stmt.executeQuery();
			log.info(METHOD_NAME, "extract data...");
			returnObject = resultSetExtractor.extractData(rs);
			log.info(METHOD_NAME, "returning object.");
			return returnObject;

		} catch (final ClassNotFoundException e) {
			log.error(METHOD_NAME, "ClassNotFoundException", e);
			throw e;
		} catch (final IOException e) {
			log.error(METHOD_NAME, "IOException", e);
			throw e;
		} finally {
			ConnectionManager.closeConnection(conn, stmt, rs);
		}

	}

	@Override
	public int executeUpdate() throws SQLException, NamingException, ClassNotFoundException, IOException {
		final String METHOD_NAME = "executeUpdate";
		log.info(METHOD_NAME, "dao: " + super.getClass().getSimpleName());

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManagerFactory.getInstance().getConnection();
			final String sql = componiQuery();
			stmt = conn.prepareStatement(sql);
			log.info(METHOD_NAME, "map statement params...");
			statementMapper.mapStatementParameters(stmt);
			log.info(METHOD_NAME, "start execute query: " + sql);
			final int result = stmt.executeUpdate();
			log.info(METHOD_NAME, "returning result: " + result);
			return result;

		} catch (final SQLException e) {
			log.error(METHOD_NAME, "SQLException", e);
			throw e;
		} finally {
			ConnectionManager.closeConnection(conn, stmt);
		}
	}
}
