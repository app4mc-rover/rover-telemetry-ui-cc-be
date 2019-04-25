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

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoverTelemetry {
    public RoverTelemetry(LocalDateTime timestamp, Cores cores2, Gy521 gy5212, Hmc5883l hmc5883l2, Infrared infrared2,
			Ultrasonic ultrasonic2) {
		time=timestamp;
		cores=cores2;
		gy521=gy5212;
		hmc5883l=hmc5883l2;
		infrared=infrared2;
		ultrasonic=ultrasonic2;
		
	}
	public RoverTelemetry() {
	}
	
	LocalDateTime time;
    Cores cores;
    Gy521 gy521;
    Hmc5883l hmc5883l;
    Infrared infrared;
    Ultrasonic ultrasonic;
}