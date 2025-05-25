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

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Interface ResultSetExtractor.
 *
 * @param <T> the generic type
 */
public interface ResultSetExtractor<T> {

	/**
	 * Extract data.
	 *
	 * @param rs the rs
	 * @return the t
	 * @throws SQLException the SQL exception
	 * @throws Exception    the exception
	 */
	public T extractData(ResultSet rs) throws SQLException;

}
