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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * The Class GenericObjectArrayStatementMapper.
 */
public class GenericObjectArrayStatementMapper extends StatementMapperUtils {

	private final Object[] objs;

	/**
	 * Instantiates a new generic object array statement mapper.
	 *
	 * @param objs the objs
	 */
	public GenericObjectArrayStatementMapper(Object... objs) {
		this.objs = objs;
	}

	@Override
	public void mapStatementParameters(PreparedStatement stmt) throws SQLException {

		for (final Object o : objs) {
			setObj(stmt, o);

		}

	}

	/**
	 * Sets the obj.
	 *
	 * @param stmt the stmt
	 * @param o    the o
	 * @throws SQLException the SQL exception
	 */
	private void setObj(PreparedStatement stmt, Object o) throws SQLException {
		if (o instanceof Integer) {
			setInt(stmt, (Integer) o);
		} else if (o instanceof Double) {
			setDouble(stmt, (Double) o);
		} else if (o instanceof String) {
			setString(stmt, (String) o);
		} else if (o instanceof java.sql.Timestamp) {
			setTimestamp(stmt, (Date) o);
		} else if (o instanceof Date) {
			setDate(stmt, (Date) o);
		} else if (o instanceof BigDecimal) {
			setBigDecimal(stmt, (BigDecimal) o);
		} else if (o == null) {
			setNull(stmt);
		} else {
			throw new IllegalArgumentException("GenericObjectArrayStatementMapper: Tipo oggetto non supportato ["+  o.getClass() + "]");
		}

	}

}
