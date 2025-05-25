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

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

/**
 * The Class SqlParameter.
 */
public class SqlParameter {

	private String name;
	private int sqlType;
	private Object value;

	/**
	 * Instantiates a new sql parameter.
	 *
	 * @param name    the name
	 * @param sqlType the sql type
	 * @param value   the value
	 */
	public SqlParameter(String name, int sqlType, Object value) {
		super();
		this.name = name;
		this.sqlType = sqlType;
		this.value = value;
	}

	/**
	 * Instantiates a new sql parameter.
	 *
	 * @param name  the name
	 * @param value the value
	 */
	public SqlParameter(String name, Object value) {
		super();
		this.name = name;
		this.sqlType = getSqlType(value);
		this.value = value;
	}

	/**
	 * Gets the sql type.
	 *
	 * @param o the o
	 * @return the sql type
	 */
	private static int getSqlType(Object o) {
		if (o instanceof Integer) {
			return Types.INTEGER;
		} else if (o instanceof Double) {
			return Types.DOUBLE;
		} else if (o instanceof String) {
			return Types.VARCHAR;
		} else if (o instanceof Date) {
			return Types.DATE;
		} else if (o instanceof BigDecimal) {
			return Types.NUMERIC;
		} else {
			throw new IllegalArgumentException("getSqlType: Tipo oggetto non supportato [" + o.getClass()  + "]");
		}
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets the sql type.
	 *
	 * @return the sql type
	 */
	public int getSqlType() {
		return sqlType;
	}

	/**
	 * Sets the sql type.
	 *
	 * @param sqlType the new sql type
	 */
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

}
