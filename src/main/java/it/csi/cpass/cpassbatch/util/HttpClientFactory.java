/*-
 * ========================LICENSE_START=================================
 * CPASS Batch
 * %%
 * Copyright (C) 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbatch.util;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class HttpClientFactory {
	public HttpRequest creaRichiestaGetHttp(String urlServizio, String utenteBatch) {
		return HttpRequest.newBuilder().uri(URI.create(urlServizio)).timeout(Duration.ofMinutes(60))
				.header("Shib-Iride-IdentitaDigitale", utenteBatch + "/////0/").GET().build();
	}
}
