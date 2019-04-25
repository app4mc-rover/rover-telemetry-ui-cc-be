/*******************************************************************************
 * Copyright (c) 2019 Dortmund University of Applied Sciences and Arts and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Dortmund University of Applied Sciences and Arts - initial API and implementation
 *******************************************************************************/
package com.appstacle.telemetryui.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cores {
	public Cores(final Double core0_ex, final Double core1_ex, final Double core2_ex, final Double core3_ex) {
		this.core0 = core0_ex;
		this.core1 = core1_ex;
		this.core2 = core2_ex;
		this.core3 = core3_ex;

	}

	final double core0;
	final double core1;
	final double core2;
	final double core3;
}