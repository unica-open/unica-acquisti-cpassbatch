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
package it.csi.cpass.cpassbatch.dao;

import java.util.List;

import it.csi.cpass.cpassbatch.dao.re.CpassTSchedulazioneBatchRSE;
import it.csi.cpass.cpassbatch.dao.util.BaseDAO;
import it.csi.cpass.cpassbatch.dto.CpassTSchedulazioneBatchDTO;

/**
 * The Class FindCampoAttivitaDAO.
 */
public class GetCpassTSchedulazioneBatchDAO extends BaseDAO<List<CpassTSchedulazioneBatchDTO>> {

	public GetCpassTSchedulazioneBatchDAO() {
		setStatementParams();
		setResultSetExtractor(new CpassTSchedulazioneBatchRSE());
	}

	@Override
	public String componiQuery() {
		final StringBuilder var1 = new StringBuilder ();
		var1.append(" SELECT ");
		var1.append("     schedulazione_batch_id ");
		var1.append("    ,ente_id ");
		var1.append("    ,ente_codice ");
		var1.append("    ,nome_job ");
		var1.append("    ,attivazione ");
		var1.append("    ,note ");
		var1.append(" FROM   CPASS_T_SCHEDULAZIONE_BATCH  ");
		var1.append(" WHERE  1 = 1 ");
		var1.append("    AND attivazione = true ");
		return var1.toString();
	}

}
