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

/**
 * <p>
 * Classe delle costanti applicative.
 * </p>
 *
 */
public final class Constants {
	/**
	 * identificativo dell'applicativo.
	 */
	public static final String APPLICATION_CODE = "attiliq";
	public static final String ACTA_ERROR_SEARCH_RESULTS = "[SERQRY-E017]";

	private Constants() {
		throw new IllegalStateException("Constants class");
	}
}
