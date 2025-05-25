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

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The Interface StatementMapper.
 */
public interface StatementMapper {

	/**
	 * In questo metodo vanno mappate le bind variable della query tramite il
	 * PreparedStatement passato.
	 *
	 * @param stmt the stmt
	 * @throws SQLException the SQL exception
	 */
	public void mapStatementParameters(PreparedStatement stmt) throws SQLException;

}
