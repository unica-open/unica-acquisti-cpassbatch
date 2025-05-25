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
import java.net.http.HttpResponse;
import java.sql.SQLException;

import javax.naming.NamingException;

import it.csi.cpass.cpassbatch.util.CpassBatchEnum;

public class ControlloBatch extends BatchBase {

	private Integer numElab = 0;

	public ControlloBatch(String pathBase, String enteCodice, String utenteBatch, Integer numElab,
			String[] param) {
		this.pathBase = pathBase;
		this.utenteBatch = utenteBatch;
		this.enteCodice = enteCodice;
		this.numElab = numElab;
		this.param = param;
	}

	public void execute() throws SQLException, NamingException, IOException, InterruptedException {
		final String urlServizio = CpassBatchEnum.CONTROLLO_BATCH_IMPEGNI.getCostante();
		final HttpResponse<String> response = eseguiChiamataAggImp(urlServizio, numElab);
		logger.error(" genero l'eccezione "+ response.body());
		
		if(response.statusCode()>299 
				|| (response.body() != null && response.body().equals("400"))
				|| (response.body() != null && response.body().indexOf("400")>0)				
				) {
			logger.error(" genero l'eccezione ");
			throw new NamingException("Errore gestito e rimandato dal BE ");
		}

		logger.info("status   Code " + response.statusCode());
		logger.info("response Body " + response.body());
	}
	

	
}
