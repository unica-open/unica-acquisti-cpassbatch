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
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class DailyCachedBaseDAO.
 *
 * @param <T> the generic type
 */
public abstract class DailyCachedBaseDAO<T> extends CachedBaseDAO<T> {
	private final Logger log = LogManager.getLogger(this.getClass());

	public static Map<String, GregorianCalendar> globalCacheDate = Collections
			.synchronizedMap(new HashMap<String, GregorianCalendar>());

	@Override
	public T executeQuery() throws ClassNotFoundException, IOException, SQLException {
		final String methodName = "executeQuery";

		final GregorianCalendar now = new GregorianCalendar();
		final GregorianCalendar dateCache = getCacheDate();
		log.debug(methodName, "dateCache:" + dateCache);

		if (dateCache == null || dateCache.get(Calendar.DATE) != now.get(Calendar.DATE)
				|| dateCache.get(Calendar.MONTH) != now.get(Calendar.MONTH)
				|| dateCache.get(Calendar.YEAR) != now.get(Calendar.YEAR)) {

			final Map<String, T> cachedResult = getCacheMap();
			setCacheDate(now);

			final int cacheSize = cachedResult.size();
			cachedResult.clear();
			log.info(methodName, super.getClass().getSimpleName()+ " - Cache cleared now (Daily based). Cache size was: " + cacheSize);
		}
		return super.executeQuery();
	}

	/**
	 * Gets the cache date.
	 *
	 * @return the cache date
	 */
	private GregorianCalendar getCacheDate() {
		final String dao = this.getClass().getName();
		final GregorianCalendar date = globalCacheDate.get(dao);
		return date;
	}

	/**
	 * Sets the cache date.
	 *
	 * @param date the new cache date
	 */
	private void setCacheDate(GregorianCalendar date) {
		final String dao = this.getClass().getName();
		globalCacheDate.put(dao, date);
	}

}
