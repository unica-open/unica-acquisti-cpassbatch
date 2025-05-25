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
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import it.csi.cpass.cpassbatch.util.CpassBatchEnum;
import it.csi.cpass.cpassbatch.util.HttpClientFactory;

public class RecuperoNotificaNso extends BatchBase {

	public RecuperoNotificaNso(String pathBase, String enteCodice, String utenteBatch, String[] param) {
		this.pathBase = pathBase;
		this.utenteBatch = utenteBatch;
		this.enteCodice = enteCodice;
		this.param = param;
	}

	public void execute() throws IOException, InterruptedException {
		String urlServizio = CpassBatchEnum.RECUPERO_NOTIFICA_NSO.getCostante();
		urlServizio = creaUrl(pathBase, urlServizio, param);
		final HttpClientFactory hc = new HttpClientFactory();
		final HttpRequest request = hc.creaRichiestaGetHttp(urlServizio, utenteBatch);
		final HttpClient httpClient = HttpClient.newBuilder().build();
		HttpResponse<String> response;
		response = httpClient.send(request, BodyHandlers.ofString());
		logger.info("statusCode: ",response.statusCode());
		logger.info("body: ",response.body());

	}
}
