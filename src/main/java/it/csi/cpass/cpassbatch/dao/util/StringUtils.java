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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class StringUtils.
 */
public class StringUtils {
	private static Logger log = LogManager.getLogger(StringUtils.class);

	/**
	 * Capitalize.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String capitalize(String s) {
		return s;
	}

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
		BigDecimal res = new BigDecimal("0");
		for (final Double d : a) {
			if (d != null) {
				final BigDecimal bd = new BigDecimal(d.toString());
				res = res.add(bd);
			}
		}
		return res.doubleValue();
	}

	/**
	 * Trasforma una stringa da Pippo_pluto_paperino in PippoPlutoPaperino.
	 *
	 * @param ca the ca
	 * @return the string
	 */
	public static String capitalizeAfterUnderscore(String ca) {
		if (ca == null) {
			return null;
		}
		int i = ca.indexOf("_");
		while (i != -1) {
			ca = ca.substring(0, i) + StringUtils.capitalize(ca.substring(i + 1));
			i = ca.indexOf("_");
		}
		return ca;
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
	 */
	public static Object copyGetSetInstance(Object original) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalAccessException {
		Object s = null;
		final String message = "copyGetSetInstance";
		try {
			s = original.getClass().getDeclaredConstructor().newInstance();
		} catch (final InstantiationException e) {
			log.error(message, e);
		} catch (final IllegalAccessException e) {
			log.error(message, e);
		}

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
	 * @param file the file
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	/*
	// NB Non buttare
	public static byte[] readAllBytes(File file) throws IOException {
		FileInputStream fis = null;
		byte[] b = null;
		try {
			fis = new FileInputStream(file);
			b = new byte[(int) file.length()];
			fis.read(b);
		} catch (IOException e) {
			log.error("IOException ", e);
		}finally {
			if(fis!=null) {
				fis.close();
			}
		}
		return b;
	}
	 */
	/*
	public static byte[] readAllBytes(File file) throws IOException {
	    ByteArrayOutputStream ous = null;
	    InputStream ios = null;
	    try {
	        byte[] buffer = new byte[4096];
	        ous = new ByteArrayOutputStream();
	        ios = new FileInputStream(file);
	        int read = 0;
	        while ((read = ios.read(buffer)) != -1) {
	            ous.write(buffer, 0, read);
	        }
	    }catch (IOException e) {
			log.error("IOException ", e);
		}finally {
            if (ous != null)
                ous.close();
            if (ios != null) {
                ios.close();
            }
	    }
	    return ous.toByteArray();
	}
	 */



	public static String splitCamelCase(String s) {
		final String exp = String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])");
		return s.replaceAll(exp, " ");
	}


	public static Object replaceNull(Object val, String rep) {
		if (val == null) {
			val = rep;
		}
		return val;
	}

	/**
	 * Checks if is empty.
	 *
	 * @param value the value
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value.trim().equals("")) {
			return true;
		}
		return false;
	}

}
