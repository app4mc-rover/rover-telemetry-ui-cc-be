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
package com.appstacle.telemetryui.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.appstacle.telemetryui.payload.Accelerometer;
import com.appstacle.telemetryui.payload.Angle;
import com.appstacle.telemetryui.payload.Cores;
import com.appstacle.telemetryui.payload.Gy521;
import com.appstacle.telemetryui.payload.Gyroscope;
import com.appstacle.telemetryui.payload.Hmc5883l;
import com.appstacle.telemetryui.payload.Infrared;
import com.appstacle.telemetryui.payload.Ultrasonic;


public class TelemetryExtractor {

	/**
	 * This function extracts the specific timestamp, received by the influxdb
	 * 
	 * @param timestampWithSymbols
	 *            unclean String from database string
	 * @return extracted timestamp
	 */
	public static LocalDateTime extractTime(final String timestampWithSymbols) {
		final String rawTimestamp = timestampWithSymbols.substring(1); /* delete [ sign */
		final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		final ZonedDateTime parsed = ZonedDateTime.parse(rawTimestamp, formatter.withZone(ZoneId.of("UTC")));
		return parsed.toLocalDateTime();
	}

	/**
	 * extracts the cores and converts them to a double value to create cores object
	 * 
	 * @param core0
	 * @param core1
	 * @param core2
	 * @param core3
	 * @return returns extracted cores in an cores object
	 */
	public static Cores extractCores(final String core0, final String core1, final String core2, final String core3) {
		/** Extracted values of the cores ! */
		final Double core0_ex = Double.valueOf(core0.split("=")[1].split("}")[0]);
		final Double core1_ex = Double.valueOf(core1.split("=")[1].split("}")[0]);
		final Double core2_ex = Double.valueOf(core2.split("=")[1].split("}")[0]);
		final Double core3_ex = Double.valueOf(core3.split("=")[1].split("}")[0]);

		return new Cores(core0_ex, core1_ex, core2_ex, core3_ex);
	}

	/**
	 * extracts all needed data of Gy521 column from string into double values and
	 * creates Gy521 object
	 * 
	 * @param accel_x
	 * @param accel_y
	 * @param accel_z
	 * @param angle_x
	 * @param angle_y
	 * @param angle_z
	 * @param gyro_x
	 * @param gyro_y
	 * @param gyro_z
	 * @return Gy521 object containing all needed data
	 */
	public static Gy521 extractGy521(final String accel_x, final String accel_y, final String accel_z,
			final String angle_x, final String angle_y, final String angle_z, final String gyro_x, final String gyro_y,
			final String gyro_z) {
		final int accel_x_ex = (int) extractOneDouble(accel_x);
		final int accel_y_ex = (int) extractOneDouble(accel_y);
		final int accel_z_ex = (int) extractOneDouble(accel_z);

		final double angle_x_ex = extractOneDouble(angle_x);
		final double angle_y_ex = extractOneDouble(angle_y);
		final double angle_z_ex = extractOneDouble(angle_z);

		final int gyro_x_ex = (int) extractOneDouble(gyro_x);
		final int gyro_y_ex = (int) extractOneDouble(gyro_y);
		final int gyro_z_ex = (int) extractOneDouble(gyro_z);

		return new Gy521(new Accelerometer(accel_x_ex, accel_y_ex, accel_z_ex),
				new Angle(angle_x_ex, angle_y_ex, angle_z_ex), new Gyroscope(gyro_x_ex, gyro_y_ex, gyro_z_ex));
	}

	public static Hmc5883l extractHmc5883l(final String bearing) {
		final double bearing_ex = extractOneDouble(bearing);
		return new Hmc5883l(bearing_ex);
	}

	public static Infrared extractInfrared(final String frontLeft, final String frontRight, final String rearLeft,
			final String rearRight) {
		final double frontLeft_ex = extractOneDouble(frontLeft);
		final double frontRight_ex = extractOneDouble(frontRight);
		final double rearLeft_ex = extractOneDouble(rearLeft);
		final double rearRight_ex = extractOneDouble(rearRight);

		return new Infrared(frontLeft_ex, frontRight_ex, rearLeft_ex, rearRight_ex);
	}

	public static Ultrasonic extractUltrasonic(final String front, final String rear) {
		final double front_ex = extractOneDouble(front);
		final double rear_ex = extractOneDouble(rear);
		return new Ultrasonic(front_ex, rear_ex);
	}

	private static double extractOneDouble(final String text) {
		return Double.parseDouble(text.replaceAll("[^0-9\\.]+",
				"")); /* delete any sign except the numbers and then parse to double value */
	}

}