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
package it.csi.cpass.cpassbatch.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * The Class AgenzieValideDTO.
 */
public class CpassTSchedulazioneBatchDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2736620798135413205L;

	Integer schedulazioneBatchId;
	UUID enteId;
	String enteCodice;
	String nomeJob;
	Boolean attivazione;
	String parametri;
	String note;

	/**
	 * @return the schedulazioneBatchId
	 */
	public Integer getSchedulazioneBatchId() {
		return schedulazioneBatchId;
	}

	/**
	 * @param schedulazioneBatchId the schedulazioneBatchId to set
	 */
	public void setSchedulazioneBatchId(Integer schedulazioneBatchId) {
		this.schedulazioneBatchId = schedulazioneBatchId;
	}

	/**
	 * @return the enteId
	 */
	public UUID getEnteId() {
		return enteId;
	}

	/**
	 * @param enteId the enteId to set
	 */
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	/**
	 * @return the attivazione
	 */
	public Boolean getAttivazione() {
		return attivazione;
	}

	/**
	 * @param attivazione the attivazione to set
	 */
	public void setAttivazione(Boolean attivazione) {
		this.attivazione = attivazione;
	}

	/**
	 * @return the parametri
	 */
	public String getParametri() {
		return parametri;
	}

	/**
	 * @param parametri the parametri to set
	 */
	public void setParametri(String parametri) {
		this.parametri = parametri;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the enteCodice
	 */
	public String getEnteCodice() {
		return enteCodice;
	}

	/**
	 * @param enteCodice the enteCodice to set
	 */
	public void setEnteCodice(String enteCodice) {
		this.enteCodice = enteCodice;
	}

	/**
	 * @return the nomeJob
	 */
	public String getNomeJob() {
		return nomeJob;
	}

	/**
	 * @param nomeJob the nomeJob to set
	 */
	public void setNomeJob(String nomeJob) {
		this.nomeJob = nomeJob;
	}

}
