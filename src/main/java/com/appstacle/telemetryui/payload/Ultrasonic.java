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

@Getter @Setter
@AllArgsConstructor
public class Ultrasonic {
    public Ultrasonic(double front_ex, double rear_ex) {
		front=front_ex;
		rear=rear_ex;
	}
	double front;
    double rear;
}