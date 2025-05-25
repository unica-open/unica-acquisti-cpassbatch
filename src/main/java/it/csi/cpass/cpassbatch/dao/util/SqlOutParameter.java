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

/**
 * The Class SqlOutParameter.
 *
 * @param <T> the generic type
 */
public class SqlOutParameter<T> extends SqlParameter {

	private ResultSetExtractor<T> resultSetExtractor;

	/**
	 * Instantiates a new sql out parameter.
	 *
	 * @param name    the name
	 * @param sqlType the sql type
	 * @param value   the value
	 */
	public SqlOutParameter(String name, int sqlType, Object value) {
		super(name, sqlType, value);
	}

	/**
	 * Instantiates a new sql out parameter.
	 *
	 * @param name    the name
	 * @param sqlType the sql type
	 * @param value   the value
	 * @param rse     the rse
	 */
	public SqlOutParameter(String name, int sqlType, Object value, ResultSetExtractor<T> rse) {
		super(name, sqlType, value);
		this.resultSetExtractor = rse;

	}

	/**
	 * Gets the result set extractor.
	 *
	 * @return the result set extractor
	 */
	public ResultSetExtractor<T> getResultSetExtractor() {
		return resultSetExtractor;
	}

	/**
	 * Sets the result set extractor.
	 *
	 * @param resultSetExtractor the new result set extractor
	 */
	public void setResultSetExtractor(ResultSetExtractor<T> resultSetExtractor) {
		this.resultSetExtractor = resultSetExtractor;
	}

}
