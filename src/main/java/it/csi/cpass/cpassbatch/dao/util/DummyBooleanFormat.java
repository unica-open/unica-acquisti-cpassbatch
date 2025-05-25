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

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

/**
 * The Class DummyBooleanFormat.
 */
public class DummyBooleanFormat extends Format {

	private static final String NULL = ""; // Rappresentazione del valore null

	/**
	 *
	 */
	private static final long serialVersionUID = -8736131300618606421L;

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {

		if (obj instanceof Boolean) {
			return new StringBuffer(((Boolean) obj).toString());
		} else if (obj == null) {
			return new StringBuffer(NULL);
		}

		throw new IllegalArgumentException("L'oggetto passato deve essere di tipo String");
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		Boolean returnValue;

		if (source == null || NULL.equals(source)) {
			returnValue = null;
			pos.setIndex(1);
		} else {
			returnValue = Boolean.valueOf(source);
			pos.setIndex(source.length());
		}

		return returnValue;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) {
		final DummyBooleanFormat dbf = new DummyBooleanFormat();
		dbf.format(null);
	}
}
