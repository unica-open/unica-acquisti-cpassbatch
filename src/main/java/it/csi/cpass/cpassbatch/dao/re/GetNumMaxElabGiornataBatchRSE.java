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
package it.csi.cpass.cpassbatch.dao.re;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbatch.dao.util.ResultSetExtractor;
import it.csi.cpass.cpassbatch.dto.GetNumMaxElabGiornataBatchDTO;

/**
 * The Class GetNumMaxElabGiornataBatchRSE.
 */
public class GetNumMaxElabGiornataBatchRSE implements ResultSetExtractor<List<GetNumMaxElabGiornataBatchDTO>> {

	@Override
	public List<GetNumMaxElabGiornataBatchDTO> extractData(ResultSet rs) throws SQLException {
		final List<GetNumMaxElabGiornataBatchDTO> result = new ArrayList<>();
		final GetNumMaxElabGiornataBatchDTO rec = new GetNumMaxElabGiornataBatchDTO();
		rec.setMaxNumeElab(0);
		if (rs.next()) {
			rec.setMaxNumeElab(rs.getInt("maxNumeElab"));
		}
		result.add(rec);
		return result;
	}
}
