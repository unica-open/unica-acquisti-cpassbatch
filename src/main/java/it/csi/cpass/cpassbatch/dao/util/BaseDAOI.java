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

import java.sql.SQLException;

/**
 * The Interface BaseDAOI.
 *
 * @param <T> the generic type
 */
public interface BaseDAOI<T> {

	/**
	 * Execute query.
	 *
	 * @return the t
	 * @throws SQLException the SQL exception
	 * @throws Exception    the exception
	 */
	public T executeQuery() throws SQLException, Exception;

	/**
	 * Execute update.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 * @throws Exception    the exception
	 */
	public int executeUpdate() throws SQLException, Exception;

}
