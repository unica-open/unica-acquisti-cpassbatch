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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The Class FormatterFactoy.
 */
public class FormatterFactoy {

	/**
	 * Gets the single instance of FormatterFactoy.
	 *
	 * @param f the f
	 * @return single instance of FormatterFactoy
	 */
	public static java.text.Format getInstance(FormatterType f) {
		switch (f) {

		case STRING:
		case NULL:
			return new DummyStringFormat();

		case DATE:
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		case CURRENCY:
			final NumberFormat nf = NumberFormat.getInstance(Locale.ITALY);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			return nf;

		case BOOLEAN:
			return new DummyBooleanFormat();

		default:
			throw new IllegalArgumentException("Nessun formatter associato a " + f);

		}

	}

	/**
	 * Gets the formatter type.
	 *
	 * @param o the o
	 * @return the formatter type
	 */
	public static FormatterType getFormatterType(Object o) {

		if (o == null) {
			return FormatterType.NULL;

		} else if (o instanceof String) {
			return FormatterType.STRING;

		} else if (o instanceof BigDecimal || o instanceof Double || o instanceof Integer || o instanceof BigInteger) {
			return FormatterType.CURRENCY;

		} else if (o instanceof Date) {
			return FormatterType.DATE;

		} else if (o instanceof Boolean) {
			return FormatterType.BOOLEAN;

		}

		throw new IllegalArgumentException("Nessun formatter associato a " +  o.getClass() );
	}

	/**
	 * Gets the single instance of FormatterFactoy.
	 *
	 * @param o the o
	 * @return single instance of FormatterFactoy
	 */
	public static java.text.Format getInstance(Object o) {
		return getInstance(getFormatterType(o));
	}

}
