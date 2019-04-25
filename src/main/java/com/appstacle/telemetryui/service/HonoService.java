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

import org.eclipse.hono.client.HonoClient;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.appstacle.telemetryui.cloud_connection.HonoConnector;
import com.appstacle.telemetryui.cloud_connection.InfluxDBClient;
import com.appstacle.telemetryui.payload.CommandTelegram;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Future;

@Service
public class HonoService {

	private static final Logger log = LoggerFactory.getLogger(HonoService.class);

	@Autowired
	private HonoConnector honoConnector;

	@Autowired
	private InfluxDBClient influxDB;

	private final String honoTenantID;

	int state = 0;

	/**
	 * Creates a new client to connect to Hono Messaging and forward the received
	 * messages to a message handler of choice.
	 *
	 * @param qpidRouterHost
	 *            url of the dispatch router to connect to
	 * @param qpidRouterPort
	 *            port of the dispatch router to use
	 * @param honoUser
	 *            user to authorize with Hono Messaging
	 * @param honoPassword
	 *            password to authorize with Hono Messaging
	 * @param honoTrustedStorePath
	 *            path to the certificate file used to connect to Hono Messaging
	 * @param reconnectAttempts
	 *            maximum number of reconnects
	 * @param honoTenantId
	 *            tenant id
	 */
	public HonoService(@Value("${hono.tenant.id}") final String honoTenantID) {
		this.honoTenantID = honoTenantID;
	}

	public void sendCommand(final String honoDeviceId, final CommandTelegram commandTelegram) {
		// Create HonoClient and connect to the given Hono instance
		final Future<HonoClient> clientFuture = this.honoConnector.connectToHonoMessaging();

		log.info("Entered sendCommand " + this.honoTenantID + "::" + honoDeviceId);

		// Create CommandClient for sending Command to a specific device
		clientFuture.map(client -> {
			client.getOrCreateCommandClient(this.honoTenantID, honoDeviceId).map(commandClient -> {
				log.info("Sending command -- " + this.honoTenantID + "::" + honoDeviceId);
				// commandClient.sendCommand(commandTelegram.getCommand(),
				// Buffer.buffer(commandTelegram.getSpeed()));

				/** START String to JSON String */
				final ObjectMapper mapper = new ObjectMapper();
				String jsonInString = "";

				// Object to JSON in String
				try {
					jsonInString = mapper.writeValueAsString(commandTelegram);
				}
				catch (final JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/** END String to JSON String */

				/*
				 * final Future<BufferResult> result =
				 * commandClient.sendCommand(commandTelegram.getCommand(),
				 * Buffer.buffer(jsonInString));
				 */
				log.info(jsonInString);

				return commandClient;
			});
			return client;
		});

		while (!clientFuture.isComplete()) {
			/* wait */
		}
		log.info("isComplete");

	}

	public QueryResult getAllTelemetryData(final String honoDeviceId) {
		return this.influxDB.getAllTelemetryData(honoDeviceId);
	}
}
