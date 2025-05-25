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

import it.csi.cpass.cpassbatch.util.CpassBatchEnum;

public class Smistatore extends BatchBase {

	public Smistatore(String pathBase, String enteCodice, String utenteBatch, String[] param) {
		this.pathBase = pathBase;
		this.utenteBatch = utenteBatch;
		this.enteCodice = enteCodice;
		this.param = param;
	}

	public void execute() throws IOException, InterruptedException {
		final String urlServizio = CpassBatchEnum.SMISTATORE.getCostante();
		logger.info("urlServizio " + urlServizio);
		final HttpResponse<String> response = eseguiChiamata(urlServizio);
		logger.info("statusCode: ",response.statusCode());
		logger.info("body: ",response.body());
	}
}
