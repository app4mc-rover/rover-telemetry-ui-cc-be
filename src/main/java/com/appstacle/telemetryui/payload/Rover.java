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

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "rover")
public class Rover {

    @Column(name = "")
    LocalDateTime time;

    @Column(name = "")
    String core0;
    @Column(name = "")
    String core1;
    @Column(name = "")
    String core2;
    @Column(name = "")
    String core3;

    @Column(name = "")
    int accel_x;
    @Column(name = "")
    int accel_y;
    @Column(name = "")
    int accel_z;

    @Column(name = "")
    double angle_x;
    @Column(name = "")
    double angle_y;
    @Column(name = "")
    double angle_z;

    @Column(name = "")
    int gyro_x;
    @Column(name = "")
    int gyro_y;
    @Column(name = "")
    int gyro_z;

    @Column(name = "")
    private double bearing;

    @Column(name = "")
    private double frontLeft;
    @Column(name = "")
    private double frontRight;
    @Column(name = "")
    private double rearLeft;
    @Column(name = "")
    private double rearRight;

    @Column(name = "")
    double ultra_front;
    @Column(name = "")
    double ultra_rear;
}