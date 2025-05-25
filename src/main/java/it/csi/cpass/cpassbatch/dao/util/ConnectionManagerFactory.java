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
package it.csi.cpass.cpassbatch.dao.util;

import java.io.IOException;

/**
 * A factory for creating ConnectionManager objects.
 */
public class ConnectionManagerFactory {
	static ConnectionManager connManagerInstance = null;

	/**
	 * Instantiates a new connection manager factory.
	 */
	private ConnectionManagerFactory() {
		//ConnectionManagerFactory
	}

	/**
	 * Gets the single instance of ConnectionManagerFactory.
	 *
	 * @return single instance of ConnectionManagerFactory
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception the exception
	 */
	public static ConnectionManager getInstance() throws ClassNotFoundException, IOException  {
		if (connManagerInstance == null) {
			connManagerInstance = new ConnectionJdbc();
		}
		return connManagerInstance;
	}

}
