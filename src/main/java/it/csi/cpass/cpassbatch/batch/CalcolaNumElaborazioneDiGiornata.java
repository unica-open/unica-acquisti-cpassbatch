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
package it.csi.cpass.cpassbatch.batch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import it.csi.cpass.cpassbatch.dao.GetNumMaxElabGiornataBatchDAO;
import it.csi.cpass.cpassbatch.dto.GetNumMaxElabGiornataBatchDTO;

public class CalcolaNumElaborazioneDiGiornata extends BatchBase {

	private Integer numElab = 0;
	private String dataElab = "";
	private String tipoElab = "";

	public CalcolaNumElaborazioneDiGiornata(Integer numElab, String dataElab,String tipoElab) {
		this.numElab  = numElab;
		this.dataElab = dataElab;
		this.tipoElab = tipoElab;
	}

	public Integer execute() throws SQLException, NamingException, ClassNotFoundException, IOException {
		numElab = 0;
		final List<GetNumMaxElabGiornataBatchDTO> lista = new GetNumMaxElabGiornataBatchDAO(dataElab.trim(),tipoElab.trim()).executeQuery();
		if (lista != null && lista.get(0).getMaxNumeElab() > 0) {
			numElab = lista.get(0).getMaxNumeElab();
		}
		return numElab;
	}
}
