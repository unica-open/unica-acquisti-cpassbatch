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

public class CaricamentoAggiornamentoImpegni extends BatchBase {

	private Integer numElab = 0;

	public CaricamentoAggiornamentoImpegni(String pathBase, String enteCodice, String utenteBatch, Integer numElab,String[] param) {
		this.pathBase = pathBase;
		this.utenteBatch = utenteBatch;
		this.enteCodice = enteCodice;
		this.numElab = numElab;
		this.param = param;
	}

	public void execute() throws SQLException, NamingException, IOException, InterruptedException {
		final String urlServizio = CpassBatchEnum.CARICAMENTO_AGGIORNAMENTI_IMPEGNI.getCostante();
		final HttpResponse<String> response = eseguiChiamataAggImp(urlServizio, numElab);
		//System.out.println("Controllo "+response.statusCode());
		if(response.statusCode()>299) {
			logger.error("errore in elaborazione " + response.statusCode());
			//throw new NamingException("Errore "+response.statusCode());
		}
		logger.info("statusCode: ",response.statusCode());
		logger.info("body: ",response.body());
		logger.info("toString: ",response.toString());

	}
}
