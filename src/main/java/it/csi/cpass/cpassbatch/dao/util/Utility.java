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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.naming.NamingException;


/**
 * The Class Utility.
 */
public class Utility {
	//private static Logger log = LogManager.getLogger(Utility.class);

	/**
	 * Gets the anno corrente.
	 *
	 * @return the anno corrente
	 */
	public static Integer getAnnoCorrente() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Somma con la precisione data da BigDecimal un insieme di Double.
	 *
	 * @param a the a
	 * @return the double
	 */
	public static Double sum(Double... a) {
		BigDecimal res = BigDecimal.ZERO;
		for (final Double d : a) {
			if (d != null) {
				final BigDecimal bd = new BigDecimal(d.toString());
				res = res.add(bd);
			}
		}
		return res.doubleValue();

	}

	/**
	 * Copy get set instance.
	 *
	 * @param original the original
	 * @return the object
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object copyGetSetInstance(Object original) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//String nomeMetodo = "copyGetSetInstance";
		Object s;
		s = original.getClass().getDeclaredConstructor().newInstance();
		final Object obj = original;
		final Method[] methods = obj.getClass().getDeclaredMethods();
		for (final Method method : methods) {
			final String methodName = method.getName();
			if (methodName.startsWith("get")) {
				Object param;
				param = method.invoke(obj);
				final Method setMethod = getSetMethod(methodName, obj.getClass());
				if(setMethod!=null) {
					setMethod.invoke(s, param);
				}
			}
		}

		return s;

	}

	/**
	 * Gets the sets the method.
	 *
	 * @param methodName the method name
	 * @param clas       the clas
	 * @return the sets the method
	 */
	private static Method getSetMethod(String methodName, Class<? extends Object> clas) {
		methodName = "set" + methodName.substring(3);
		final Method[] methods = clas.getDeclaredMethods();
		for (final Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}


	/**
	 * Read all bytes.
	 *
	 * @param filePath the file path
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public static byte[] readAllBytes(String filePath) throws Exception {
		final File file = new File(filePath);
		return readAllBytes(file);
	}

	public static byte[] readAllBytes(File file) throws IOException {
		byte[] b = null;
		try (FileInputStream fis        = new FileInputStream(file);
				ByteArrayOutputStream	ous = new ByteArrayOutputStream();
				){
			b = new byte[(int) file.length()];
			int read = 0;
			while ((read = fis.read(b)) != -1) {
				ous.write(b, 0, read);
			}
			return ous.toByteArray();
		}
	}

	/**
	 * Big decimal to importo.
	 *
	 * @param d the d
	 * @return the string
	 */
	public static String bigDecimalToImporto(BigDecimal d) {
		return formatDecimal(d, "#,##0.00");
	}

	/**
	 * Format decimal.
	 *
	 * @param d       the d
	 * @param pattern the pattern
	 * @return the string
	 */
	private static String formatDecimal(BigDecimal d, String pattern) {
		if (d == null) {
			return null;
		}
		final NumberFormat f = NumberFormat.getInstance(Locale.ITALY);
		if (f instanceof DecimalFormat) {
			final DecimalFormat decimalFormat = (DecimalFormat) f;
			decimalFormat.applyPattern(pattern);
			return decimalFormat.format(d);
		}
		return null;
	}

	/**
	 * Split camel case.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String splitCamelCase(String s) {
		final String exp = String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])");
		return s.replaceAll(exp, " ");
	}

	/**
	 * Execute query.
	 *
	 * @param <T> the generic type
	 * @param dao the dao
	 * @return the t
	 * @throws SQLException    the SQL exception
	 * @throws NamingException the naming exception
	 * @throws RemoteException the remote exception
	 * @throws Exception       the exception
	 */
	public <T> T executeQuery(BaseDAOI<T> dao) throws SQLException, NamingException, RemoteException, Exception {
		return dao.executeQuery();
	}
}
