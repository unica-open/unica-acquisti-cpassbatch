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
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class CachedBaseDAO.
 *
 * @param <T> the generic type
 */
public abstract class CachedBaseDAO<T> extends BaseDAO<T> {

	private final Logger log = LogManager.getLogger(this.getClass());

	private static Map<String, Map<String, Object>> globalCacheMap = Collections.synchronizedMap(new HashMap<String, Map<String, Object>>());

	@Override
	public T executeQuery() throws ClassNotFoundException, IOException, SQLException {
		final String methodName = "executeQuery";

		final Map<String, T> cachedResult = getCacheMap();

		final String key = getCacheKey();

		log.debug(methodName, "cache:" + cachedResult);

		synchronized (cachedResult) {
			if (cachedResult.containsKey(key)) {
				log.info(methodName, super.getClass().getSimpleName() + " - returning cached result for key: " + key);
				return cachedResult.get(key);
			}
		}
		final T result = super.executeQuery();
		cachedResult.put(key, result);
		return result;
	}

	/**
	 * Chiave per il quale verrà inserita un'entry nella mappa di cache. Tale chiave
	 * deve essere fornita univoca rispetto alla query che si sta eseguendo. Ad
	 * esempio può essere composta dalla concatenazione dei campi di ricerca.
	 *
	 * @return the cache key
	 */
	protected abstract String getCacheKey();

	/**
	 * Mappa rappresentante la cache dei risultati della query.
	 *
	 * @return the cache map
	 */

	@SuppressWarnings("unchecked")
	protected Map<String, T> getCacheMap() {
		final String dao = this.getClass().getName();
		Map<String, T> map = (Map<String, T>) globalCacheMap.get(dao);
		if (map.isEmpty()) {
			map = Collections.synchronizedMap(new HashMap<String, T>());
			globalCacheMap.put(dao, (Map<String, Object>) map);
		}
		return map;
	}

}
