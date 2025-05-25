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
import java.util.UUID;

import it.csi.cpass.cpassbatch.dao.util.ResultSetExtractor;
import it.csi.cpass.cpassbatch.dto.CpassTSchedulazioneBatchDTO;

/**
 * The Class FindCampoAttivitaRSE.
 */
public class CpassTSchedulazioneBatchRSE implements ResultSetExtractor<List<CpassTSchedulazioneBatchDTO>> {

	@Override
	public List<CpassTSchedulazioneBatchDTO> extractData(ResultSet rs) throws SQLException {
		final List<CpassTSchedulazioneBatchDTO> result = new ArrayList<>();

		while (rs.next()) {
			final CpassTSchedulazioneBatchDTO rec = new CpassTSchedulazioneBatchDTO();
			rec.setSchedulazioneBatchId(rs.getInt("schedulazione_batch_id"));
			rec.setEnteId((UUID) rs.getObject("ente_id"));
			rec.setEnteCodice(rs.getString("ente_codice"));
			rec.setNomeJob(rs.getString("nome_job"));
			rec.setAttivazione(rs.getBoolean("attivazione"));
			rec.setNote(rs.getString("note"));
			result.add(rec);
		}
		return result;
	}

}
