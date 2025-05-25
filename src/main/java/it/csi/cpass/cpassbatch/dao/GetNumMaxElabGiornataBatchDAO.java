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

import it.csi.cpass.cpassbatch.dao.re.GetNumMaxElabGiornataBatchRSE;
import it.csi.cpass.cpassbatch.dao.util.BaseDAO;
import it.csi.cpass.cpassbatch.dto.GetNumMaxElabGiornataBatchDTO;

/**
 * The Class FindCampoAttivitaDAO.
 */
public class GetNumMaxElabGiornataBatchDAO extends BaseDAO<List<GetNumMaxElabGiornataBatchDTO>> {

	String dataElab = "";
	String tipoElab = "";
	public GetNumMaxElabGiornataBatchDAO(String dataElab,String tipoElab) {
		this.dataElab   = dataElab;
		this.tipoElab = tipoElab;
		setStatementParams();
		setResultSetExtractor(new GetNumMaxElabGiornataBatchRSE());
	}

	@Override
	public String componiQuery() {
		final StringBuilder strSql = new StringBuilder ();
		strSql.append(" SELECT MAX(COALESCE((fie.num_elaborazione_di_giornata),0)) maxNumeElab ");
		strSql.append(" FROM ");
		strSql.append("   cpass_t_elaborazione fie  ");
		strSql.append("  ,cpass_d_elaborazione_tipo tipo  ");
		strSql.append(" WHERE ");
		strSql.append("     fie.elaborazione_tipo_id = tipo.elaborazione_tipo_id ");
		strSql.append(" AND tipo.elaborazione_tipo_codice IN("+tipoElab+")");
		strSql.append(" AND fie.data_elaborazione_di_giornata like '%" + dataElab.trim() + "%'");

		return strSql.toString();
		//return " SELECT MAX(COALESCE((fie.num_elaborazione_di_giornata),0)) maxNumeElab FROM Cpass_T_Flusso_Impegni_Esterni fie  WHERE fie.data_elaborazione like '%" + dataElab.trim() + "%'";
	}

}
