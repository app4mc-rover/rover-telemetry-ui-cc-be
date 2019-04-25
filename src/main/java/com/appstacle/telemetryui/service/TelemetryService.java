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
package com.appstacle.telemetryui.service;

import java.time.LocalDateTime;

import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.appstacle.telemetryui.payload.Cores;
import com.appstacle.telemetryui.payload.Gy521;
import com.appstacle.telemetryui.payload.Hmc5883l;
import com.appstacle.telemetryui.payload.Infrared;
import com.appstacle.telemetryui.payload.RoverTelemetry;
import com.appstacle.telemetryui.payload.Ultrasonic;
import com.appstacle.telemetryui.util.TelemetryExtractor;

@Component
public class TelemetryService {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private HonoService honoService;

	private final String deviceIDs[];

	public TelemetryService(@Value("${hono.device.ids}") final String deviceIDs[]) {
		this.deviceIDs = deviceIDs;
	}

	@Scheduled(fixedDelay = 1000)
	public void publishUpdates() {

		for (int i = 0; i < this.deviceIDs.length; i++) {
			final RoverTelemetry telemetryData = getTelemetry(this.deviceIDs[i]);
			// log.info("RoverID: " + deviceIDs[i]);

			if (telemetryData != null) {
				// log.info("Updated Telemetry Data", telemetryData);
				this.template.convertAndSend("/topic/" + this.deviceIDs[i] + "/telemetry", telemetryData);
			}
		}
	}

	public RoverTelemetry getAllRoverTelemetry() {
		return new RoverTelemetry();
	}

	public RoverTelemetry getTelemetry(final String honoDeviceId) {
		/** START DATA VARIABLE */
		LocalDateTime timestamp = null;
		Cores cores = null;
		Gy521 gy521 = null;
		Hmc5883l hmc5883l = null;
		Infrared infrared = null;
		Ultrasonic ultrasonic = null;
		/** END DATA VARIABLE */

		/* Read the latest 5 entries with each column */
		final QueryResult result = this.honoService.getAllTelemetryData(honoDeviceId);

		/*
		 * Get the latest entry of five. Five entries have to be read otherwise
		 * there is no data. The sampling rate of the rover is 1 second and too
		 * slow !
		 */
		if (result != null) {
			if (result.getResults().get(0).getSeries() != null) {
				final int lastElement = result.getResults().get(0).getSeries().get(0).getValues().size() - 1;
				final String currentData = result.getResults().get(0).getSeries().get(0).getValues().get(lastElement)
						.toString();
				if (result.getResults().get(0)
						.getSeries() != null) { /* extract all needed data */
					final String currentDataArray[] = currentData.split(",");
					timestamp = TelemetryExtractor.extractTime(currentDataArray[0]);
					cores = TelemetryExtractor.extractCores(currentDataArray[1], currentDataArray[2],
							currentDataArray[3], currentDataArray[4]);
					gy521 = TelemetryExtractor.extractGy521(currentDataArray[5], currentDataArray[6],
							currentDataArray[7], currentDataArray[8], currentDataArray[9], currentDataArray[10],
							currentDataArray[11], currentDataArray[12], currentDataArray[13]);
					hmc5883l = TelemetryExtractor.extractHmc5883l(currentDataArray[14]);
					infrared = TelemetryExtractor.extractInfrared(currentDataArray[15], currentDataArray[16],
							currentDataArray[17], currentDataArray[18]);

					ultrasonic = TelemetryExtractor.extractUltrasonic(currentDataArray[19], currentDataArray[20]);
					return new RoverTelemetry(timestamp, cores, gy521, hmc5883l, infrared, ultrasonic);
				}
			}
		}
		return null;
	}
}