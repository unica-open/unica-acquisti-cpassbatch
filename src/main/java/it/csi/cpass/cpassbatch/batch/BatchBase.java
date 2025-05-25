/*-
 * ========================LICENSE_START=================================
 * CPASS Batch
 * %%
 * Copyright (C) 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbatch.batch;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.SQLException;
import java.time.Duration;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.csi.cpass.cpassbatch.util.HttpClientFactory;

public class BatchBase {

	private static final String URL_CON_SOSTITUZIONE_AVVENUTA = "url con sostituzione avvenuta ";
	protected final Logger logger = LogManager.getLogger(getClass());
	protected String pathBase = "";
	protected String enteCodice = "";
	protected String utenteBatch = "";
	protected String[] param;

	/**
	 *
	 * @param pathBase
	 * @param urlServizio
	 * @param par
	 * @return
	 */
	protected String creaUrl(String pathBase, String urlServizio, String[] par) {
		String url = pathBase + urlServizio;
		url = url.replace("{enteCodice}", enteCodice);

		for (int i = 1; i < par.length; i++) {
			url = url.replace("{" + i + "}", par[i].trim());
			logger.info("parametro " + i + " valore: " + par[i].trim());
		}
		logger.info(URL_CON_SOSTITUZIONE_AVVENUTA + url);
		return url;
	}

	/**
	 *
	 * @param pathBase
	 * @param urlServizio
	 * @param numelab
	 * @param dataElab
	 * @return
	 */
	protected String creaUrlAggImp(String pathBase, String urlServizio, Integer numelab, String dataElab) {
		String url = pathBase + urlServizio;
		url = url.replace("{enteCodice}", enteCodice);
		url = url.replace("{1}", String.valueOf(numelab));
		url = url.replace("{2}", dataElab.trim());
		logger.info(URL_CON_SOSTITUZIONE_AVVENUTA + url);
		return url;
	}

	/**
	 * @param urlServizio
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	protected HttpResponse<String> eseguiChiamata(String urlServizio) throws IOException, InterruptedException {
		urlServizio = creaUrl(pathBase, urlServizio, param);
		logger.info("urlServizio --> " + urlServizio);
		final HttpClientFactory hc = new HttpClientFactory();
		final HttpRequest request = hc.creaRichiestaGetHttp(urlServizio, utenteBatch);
		final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(60)).build();
		HttpResponse<String> response;
		response = httpClient.send(request, BodyHandlers.ofString());
		return response;
	}

	/**
	 *
	 * @param urlServizio
	 * @return
	 * @throws Exception
	 * @throws NamingException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	protected HttpResponse<String> eseguiChiamataAggImp(String urlServizio, Integer numElab)throws SQLException, NamingException, IOException, InterruptedException {
		final String dataElab = param[2].trim();
		urlServizio = creaUrlAggImp(pathBase, urlServizio, numElab, dataElab);
		logger.info("urlServizio --> " + urlServizio);
		final HttpClientFactory hc = new HttpClientFactory();
		final HttpRequest request = hc.creaRichiestaGetHttp(urlServizio, utenteBatch);
		final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(60)).build();
		HttpResponse<String> response;
		response = httpClient.send(request, BodyHandlers.ofString());
		return response;
	}

}
