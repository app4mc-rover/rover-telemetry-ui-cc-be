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
public class Gy521 {
	public Gy521(final Accelerometer accelerometer2, final Angle angle2, final Gyroscope gyroscope2) {
		this.accelerometer = accelerometer2;
		this.angle = angle2;
		this.gyroscope = gyroscope2;
	}

	Accelerometer accelerometer;
	Angle angle;
	Gyroscope gyroscope;
}