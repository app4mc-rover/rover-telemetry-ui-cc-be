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
public class Infrared {
	public Infrared(final double frontLeft_ex, final double frontRight_ex, final double rearLeft_ex,
			final double rearRight_ex) {
		this.frontLeft = frontLeft_ex;
		this.frontRight = frontRight_ex;
		this.rearLeft = rearLeft_ex;
		this.rearRight = rearRight_ex;
	}

	double frontLeft;
	double frontRight;
	double rearLeft;
	double rearRight;
}