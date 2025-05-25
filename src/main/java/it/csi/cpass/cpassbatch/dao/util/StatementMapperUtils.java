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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class StatementMapperUtils.
 */
public abstract class StatementMapperUtils implements StatementMapper {
	private static final String PARAMENTRO_OBBLIGATORIO_MANCANTE = "Paramentro obbligatorio mancante";

	private final Logger log = LogManager.getLogger(this.getClass());

	private int n = 1;

	/**
	 * Sets the int.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setInt(PreparedStatement stmt, Integer value) throws SQLException {
		setInt(stmt, value, false);
	}

	/**
	 * Sets the int.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setInt(PreparedStatement stmt, Integer value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setInt(n, value.intValue());
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.INTEGER);
			}
		}
		log.debug("setInt", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the int0.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setInt0(PreparedStatement stmt, Integer value) throws SQLException {
		if (value != null) {
			stmt.setInt(n, value.intValue());
		} else {
			stmt.setInt(n, 0);
		}
		log.debug("setInt0", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the int.
	 *
	 * @param stmt the stmt
	 * @param num  the num
	 * @throws SQLException the SQL exception
	 */
	public void setInt(PreparedStatement stmt, int num) throws SQLException {
		stmt.setInt(n, num);
		log.debug("setInt", n + ". " + num);
		n = n + 1;
	}

	/**
	 * Sets the int if present.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setIntIfPresent(PreparedStatement stmt, Integer value) throws SQLException {
		if (value != null) {
			stmt.setInt(n, value.intValue());
			log.debug("setIntIfPresent", n + ". " + value);
			n = n + 1;
		}

	}

	/**
	 * Sets the double.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setDouble(PreparedStatement stmt, Double value) throws SQLException {
		setDouble(stmt, value, false);
	}

	/**
	 * Sets the double.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setDouble(PreparedStatement stmt, Double value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setDouble(n, value.doubleValue());
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.DOUBLE);
			}
		}
		log.debug("setDouble", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the big decimal.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setBigDecimal(PreparedStatement stmt, BigDecimal value) throws SQLException {
		setBigDecimal(stmt, value, false);
	}

	/**
	 * Sets the big decimal.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setBigDecimal(PreparedStatement stmt, BigDecimal value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setBigDecimal(n, value);
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.DOUBLE);
			}
		}
		log.debug("setBigDecimal", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the double0.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setDouble0(PreparedStatement stmt, Double value) throws SQLException {
		if (value != null) {
			stmt.setDouble(n, value.doubleValue());
		} else {
			stmt.setDouble(n, 0);
		}
		log.debug("setDouble0", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the double if present.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setDoubleIfPresent(PreparedStatement stmt, Double value) throws SQLException {
		if (value != null) {
			stmt.setDouble(n, value.doubleValue());
			log.debug("setDoubleIfPresent", n + ". " + value);
			n = n + 1;
		}

	}

	/**
	 * Sets the string upper case.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setStringUpperCase(PreparedStatement stmt, String value) throws SQLException {
		setStringUpperCase(stmt, value, false);
	}

	/**
	 * Sets the string upper case.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setStringUpperCase(PreparedStatement stmt, String value, boolean obbligatorio) throws SQLException {

		if (value != null) {
			stmt.setString(n, value.toUpperCase());
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.VARCHAR);
			}
		}
		log.debug("setStringUpperCase", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the null.
	 *
	 * @param stmt the new null
	 * @throws SQLException the SQL exception
	 */
	public void setNull(PreparedStatement stmt) throws SQLException {
		stmt.setNull(n, java.sql.Types.NULL);
		log.debug("setNull", n + ". null");
		n = n + 1;
	}

	/**
	 * Sets the string.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setString(PreparedStatement stmt, String value) throws SQLException {
		setString(stmt, value, false);
	}

	/**
	 * Sets the string.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setString(PreparedStatement stmt, String value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setString(n, value);
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.VARCHAR);
			}
		}
		log.debug("setString", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the string if present.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setStringIfPresent(PreparedStatement stmt, String value) throws SQLException {
		if (value != null) {
			stmt.setString(n, value);
			log.debug("setStringIfPresent", n + ". " + value);
			n = n + 1;
		}

	}

	/**
	 * Sets the date.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setDate(PreparedStatement stmt, java.util.Date value) throws SQLException {
		setDate(stmt, value, false);
	}

	/**
	 * Sets the date.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setDate(PreparedStatement stmt, java.util.Date value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setDate(n, new java.sql.Date(value.getTime()));
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.DATE);
			}
		}
		log.debug("setDate", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Sets the date if present.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setDateIfPresent(PreparedStatement stmt, java.util.Date value) throws SQLException {
		if (value != null) {
			stmt.setDate(n, new java.sql.Date(value.getTime()));
			log.debug("setDateIfPresent", n + ". " + value);
			n = n + 1;
		}

	}

	/**
	 * Sets the timestamp.
	 *
	 * @param stmt  the stmt
	 * @param value the value
	 * @throws SQLException the SQL exception
	 */
	public void setTimestamp(PreparedStatement stmt, java.util.Date value) throws SQLException {
		setTimestamp(stmt, value, false);
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param stmt         the stmt
	 * @param value        the value
	 * @param obbligatorio the obbligatorio
	 * @throws SQLException the SQL exception
	 */
	public void setTimestamp(PreparedStatement stmt, java.util.Date value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setTimestamp(n, new java.sql.Timestamp(value.getTime()));
		} else {
			if (obbligatorio) {
				throw new SQLException(PARAMENTRO_OBBLIGATORIO_MANCANTE);
			} else {
				stmt.setNull(n, java.sql.Types.TIMESTAMP);
			}
		}
		log.debug("setTimestamp", n + ". " + value);
		n = n + 1;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	public void setN(int n) {
		this.n = n;
	}

}
