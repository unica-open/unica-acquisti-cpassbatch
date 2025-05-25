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
package it.csi.cpass.cpassbatch.util;

/**
 * Enum for Cpass
 */
public enum CpassBatchEnum {
	/** batch VERIFICA_INVIO_CONTABILITA */
	CARICAMENTO_AGGIORNAMENTI_IMPEGNI("batch/caricamento-aggiornamenti-impegni/ente/{enteCodice}/numelab/{1}/dataElab/{2}"),
	CARICAMENTO_AGGIORNAMENTI_SUBIMPEGNI("batch/caricamento-aggiornamenti-subimpegni/ente/{enteCodice}/numelab/{1}/dataElab/{2}"),
	AGGIORNAMENTI_SUBIMPEGNI("batch/aggiornamenti-subimpegni/ente/{enteCodice}/numelab/{1}/dataElab/{2}"),
	CONTROLLO_BATCH_IMPEGNI("batch/controllo-batch-impegni/ente/{enteCodice}/numelab/{1}/dataElab/{2}"),
	VERIFICA_INVIO_CONTABILITA("batch/verifica-invio-contabilita/ente/{enteCodice}"),
	RECUPERO_DDT("batch/recupero-ddt/ente/{enteCodice}"),
	RECUPERO_NOTIFICA_NSO("batch/recupero-notifica-nso/ente/{enteCodice}"),
	SMISTATORE("batch/smistamento/ente/{enteCodice}"),
	STORICIZZA_FILE_DDT("batch/storico-file-ddt/ente/{enteCodice}"),
	STORICIZZA_FILE_NSO("batch/storico-file-nso/ente/{enteCodice}"),
	AGG_STRUTTURA("batch/aggiornamento-struttura/ente/{enteCodice}"),
	AGGIORNAMENTI_IMPEGNI("batch/aggiornamenti-impegni/ente/{enteCodice}/numelab/{1}/dataElab/{2}");

	private final String costante;

	private CpassBatchEnum(String costante) {
		this.costante = costante;
	}

	private CpassBatchEnum() {
		this.costante = this.name();
	}

	/**
	 * @return the costante
	 */
	public String getCostante() {
		return costante;
	}

}
