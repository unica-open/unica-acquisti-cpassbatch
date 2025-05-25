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
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ConnectionJdbc.
 */
public class ConnectionJdbc extends ConnectionManager {
	private static Logger log = LogManager.getLogger(ConnectionJdbc.class);

	String url = null;
	String driver = null;
	String user = null;
	String pass = null;
	long incrementalRetryMills;
	int maxRetryAttemps;
	long retryAttemps = 0;

	/**
	 * Instantiates a new connection jdbc.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws Exception              the exception
	 */
	protected ConnectionJdbc() throws ClassNotFoundException, IOException {
		final String methodName = "ConnectionJdbc";
		final InputStream is = ClassLoader.getSystemResourceAsStream("db.properties");
		final Properties props = new Properties();
		props.load(is);
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
		try {
			incrementalRetryMills = Long.parseLong(props.getProperty("incrementalRetryMills"));
		} catch (final NumberFormatException nfe) {
			incrementalRetryMills = 1000;
		}
		try {
			maxRetryAttemps = Integer.parseInt(props.getProperty("maxRetryAttemps"));
		} catch (final NumberFormatException nfe) {
			maxRetryAttemps = 10;
		}
		log.info(methodName, "driver: "+driver);
		log.info(methodName, "url: "+url);
		log.info(methodName, "user: "+user);
		log.info(methodName, "pass: "+pass);
		log.info(methodName, "incrementalRetryMills: " + incrementalRetryMills);
		log.info(methodName, "maxRetryAttemps: " + maxRetryAttemps);
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			retryAttemps = 0;
			return conn;
		} catch (final SQLException e) {
			if (e.getMessage().indexOf("The Network Adapter could not establish the connection") != -1
					&& retryAttemps < maxRetryAttemps) {
				log.info("getConnection",
						"The Network Adapter could not establish the connection. Retry attemp: " + (retryAttemps + 1));
				incrementalThreadSleep();
				return getConnection();
			}
			throw e;
		}
	}

	/**
	 * Incremental thread sleep.
	 */
	private void incrementalThreadSleep() {
		try {
			Thread.sleep(retryAttemps * incrementalRetryMills);
		} catch (final InterruptedException e1) {
			log.error("incrementalThreadSleep", e1);
			Thread.currentThread().interrupt();
		}
		retryAttemps++;
	}
	/*
	public static void main(String[] args) {
		try {
			try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cpass", "cpass","cpass")) {
				try (Statement stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT schedulazione_batch_id FROM   CPASS_T_SCHEDULAZIONE_BATCH")) {
				}
			}
		} catch (SQLException e) {
			log.error("Erroe", e.getMessage());
			System.exit(0);
		}
	}
	 */
}
