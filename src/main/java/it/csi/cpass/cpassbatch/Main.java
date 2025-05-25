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
package it.csi.cpass.cpassbatch;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.csi.cpass.cpassbatch.batch.AggiornamentoImpegni;
import it.csi.cpass.cpassbatch.batch.AggiornamentoStruttura;
import it.csi.cpass.cpassbatch.batch.AggiornamentoSubImpegni;
import it.csi.cpass.cpassbatch.batch.CalcolaNumElaborazioneDiGiornata;
import it.csi.cpass.cpassbatch.batch.CaricamentoAggiornamentoImpegni;
import it.csi.cpass.cpassbatch.batch.CaricamentoAggiornamentoSubImpegni;
import it.csi.cpass.cpassbatch.batch.ControlloBatch;
import it.csi.cpass.cpassbatch.batch.RecuperoDocumentoTrasporto;
import it.csi.cpass.cpassbatch.batch.RecuperoNotificaNso;
import it.csi.cpass.cpassbatch.batch.Smistatore;
import it.csi.cpass.cpassbatch.batch.StoricizzaDdt;
import it.csi.cpass.cpassbatch.batch.StoricizzaNso;
import it.csi.cpass.cpassbatch.batch.VerificaInvioContabilita;
import it.csi.cpass.cpassbatch.dao.GetCpassTSchedulazioneBatchDAO;
import it.csi.cpass.cpassbatch.dto.CpassTSchedulazioneBatchDTO;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		final String nomeBatch = args[0];
		String enteCodice = "";
		final Properties prop = caricaFileProperty();
		final String pathBase = prop.getProperty("path_base");
		final String utenteBatch = prop.getProperty("header_user_shib");
		List<CpassTSchedulazioneBatchDTO> lista = new ArrayList<>();
		try {
			//System.out.println("Main chiamata batch");
			lista = new GetCpassTSchedulazioneBatchDAO().executeQuery();
			boolean chiamataEseguita = false;
			for (final CpassTSchedulazioneBatchDTO r : lista) {
				//System.out.println("dentro la lista dei batch abilitati");

				enteCodice = r.getEnteCodice();
				//System.out.println("enteCodice " + enteCodice);

				logger.info("nome1 " + r.getNomeJob() + " niome2--> " + nomeBatch);
				if (r.getNomeJob().equals(nomeBatch)) {
					chiamataEseguita = true;
					eseguiChiamataBatch(enteCodice, nomeBatch, args, pathBase, utenteBatch);
					logger.info("chiama eseguita ");
				}
			}
			if (!chiamataEseguita) {
				logger.warn("nessuna chiamata eseguita controllare la tabella di mappatura ed attivazione del batch "+ nomeBatch);
			}
			System.exit(0);
		} catch (final IOException e) {
			logger.error("IOException : " + e.getMessage(), e);
			System.exit(-1);
		} catch (final NamingException e) {
			logger.error("NamingException : " + e.getMessage(), e);
			System.exit(-1);
		}catch (final ClassNotFoundException e) {
			logger.error("ClassNotFoundException : " + e.getMessage(), e);
			System.exit(-1);
		} catch (final SQLException e) {
			logger.error("SQLException : " + e.getMessage(), e);
			System.exit(-1);
		}
	}

	/**
	 * @param args
	 * @param nomeBatch
	 * @param pathBase
	 * @param utenteBatch
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	protected static void eseguiChiamataBatch(String enteCodice, String nomeBatch, String[] args, String pathBase,String utenteBatch) throws SQLException, NamingException, ClassNotFoundException {
		try {
			switch (nomeBatch) {
			case "VERIFICA_INVIO_CONTABILITA":
				new VerificaInvioContabilita(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			case "AGGIORNAMENTO_IMPEGNI":
				Integer numElab = Integer.parseInt(args[1].trim());
				final String dataElab = args[2].trim();
				if (numElab == 0) {
					logger.error("Caso in cui dallo script ho il parametro a 0 per cui calcolo");
					numElab = new CalcolaNumElaborazioneDiGiornata(numElab, dataElab,"'CARICA_IMPEGNO_TMP','CARICA_SUBIMPEGNO_TMP','AGG_IMPEGNO_TMP','AGG_SUBIMPEGNO_TMP'").execute();
					numElab = numElab + 1;
				} else {
					logger.error("Caso in cui lo script mi passa un valore diverso da 0 " + numElab);
				}
				//System.out.println("step1 dello step 4");
				new CaricamentoAggiornamentoImpegni(pathBase, enteCodice, utenteBatch, numElab, args).execute();
				//System.out.println("step2");
				new CaricamentoAggiornamentoSubImpegni(pathBase, enteCodice, utenteBatch, numElab, args).execute();
				//System.out.println("step3");
				new AggiornamentoImpegni(pathBase, enteCodice, utenteBatch, numElab, args).execute();
				//System.out.println("step4");
				new AggiornamentoSubImpegni(pathBase, enteCodice, utenteBatch, numElab, args).execute();
				//System.out.println("step5");
				new ControlloBatch(pathBase, enteCodice, utenteBatch, numElab, args).execute();
				//System.out.println("dopo il 5");
				break;
			case "DDT":
				new RecuperoDocumentoTrasporto(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			case "RECUPERO_NOTIFICA_NSO":
				new RecuperoNotificaNso(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			case "SMISTATORE":
				new Smistatore(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			case "STORICO_FILE_DDT":
				new StoricizzaDdt(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			case "STORICO_FILE_NSO":
				new StoricizzaNso(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			case "AGG_STRUTTURA":
				new AggiornamentoStruttura(pathBase, enteCodice, utenteBatch, args).execute();
				break;
			default:
				logger.error("Batch Non censito a sistema " + nomeBatch);
				System.exit(1);
				return;
			}
		} catch (final IOException e) {
			logger.error("IOException " + nomeBatch,e);
			System.exit(1);
			return;
		} catch (final InterruptedException e) {
			logger.error("InterruptedException AA" + nomeBatch,e);
			Thread.currentThread().interrupt();
			System.exit(1);
			return;
		} catch (final ClassNotFoundException e) {
			logger.error("ClassNotFoundException " + nomeBatch,e);
			System.exit(1);
			return;
		}
	}

	private static Properties caricaFileProperty() {
		final Properties properties = new Properties();
		try (final InputStream stream = Main.class.getResourceAsStream("/config.properties")) {
			properties.load(stream);
		} catch (final IOException e) {
			logger.error("Lettura file properties: " + e.getMessage(), e);
			System.exit(2);
		}
		return properties;
	}
}