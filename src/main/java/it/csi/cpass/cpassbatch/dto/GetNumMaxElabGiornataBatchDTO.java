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

/**
 * The Class AgenzieValideDTO.
 */
public class GetNumMaxElabGiornataBatchDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8825470726942921985L;
	Integer maxNumeElab;

	/**
	 * @return the maxNumeElab
	 */
	public Integer getMaxNumeElab() {
		return maxNumeElab;
	}

	/**
	 * @param maxNumeElab the maxNumeElab to set
	 */
	public void setMaxNumeElab(Integer maxNumeElab) {
		this.maxNumeElab = maxNumeElab;
	}

}
