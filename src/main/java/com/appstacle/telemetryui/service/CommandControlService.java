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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.appstacle.telemetryui.dto.CommandDTO;
import com.appstacle.telemetryui.util.HonoConnector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Future;

@Service
public class CommandControlService {

	private static final Logger log = LoggerFactory.getLogger(CommandControlService.class);

	@Autowired
	private HonoConnector honoConnector;

	private final String honoTenantID;

	int state = 0;

	public CommandControlService(@Value("${hono.tenant.id}") final String honoTenantID) {
		this.honoTenantID = honoTenantID;
	}

	public void sendCommand(final String honoDeviceId, final CommandDTO command) {
		// Create HonoClient and connect to the given Hono instance
		final Future<HonoClient> clientFuture = this.honoConnector.connectToHonoMessaging();

		log.info("Entered sendCommand " + this.honoTenantID + "::" + honoDeviceId);

		// Create CommandClient for sending Command to a specific device
		clientFuture.map(client -> {
			client.getOrCreateCommandClient(this.honoTenantID, honoDeviceId).map(commandClient -> {
				log.info("Sending command -- " + this.honoTenantID + "::" + honoDeviceId);
				commandClient.sendOneWayCommand("test", null);
				return commandClient;
			});
			return client;
		});

		while (!clientFuture.isComplete()) {
			/* wait */
		}
		log.info("isComplete");

	}
}
