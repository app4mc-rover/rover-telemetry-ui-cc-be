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
import lombok.Setter;
import lombok.Getter;


@Getter
@Setter
@AllArgsConstructor
public class Gyroscope {
    public Gyroscope(int gyro_x_ex, int gyro_y_ex, int gyro_z_ex) {
    		x=gyro_x_ex;
    		y=gyro_y_ex;
    		z=gyro_z_ex;
    }
	int x;
    int y;
    int z;
}