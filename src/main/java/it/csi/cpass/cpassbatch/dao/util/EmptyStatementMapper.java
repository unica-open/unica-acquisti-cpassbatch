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
 * The Class EmptyStatementMapper.
 */
public class EmptyStatementMapper implements StatementMapper {

	@Override
	public void mapStatementParameters(PreparedStatement stmt) throws SQLException {
		// Do nothing.
	}

}
