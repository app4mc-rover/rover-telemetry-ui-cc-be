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
package com.appstacle.telemetryui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appstacle.telemetryui.payload.CommandTelegram;
import com.appstacle.telemetryui.payload.RoverTelemetry;
import com.appstacle.telemetryui.service.HonoService;
import com.appstacle.telemetryui.service.TelemetryService;

// @CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(path = "/rover")
public class RoverController {

	private static final Logger log = LoggerFactory.getLogger(RoverController.class);

	@Autowired
	private HonoService honoService;

	@Autowired
	private TelemetryService telemetryService;

	/*
	 * This function returns the lastest telemetry entry of the database on request
	 */
	@GetMapping(path = "{roverID}/telemetry")
	public RoverTelemetry getTelemetry(@PathVariable final String roverID) {
		return this.telemetryService.getTelemetry(roverID);
	}

	@GetMapping(path = "/rovers")
	public RoverTelemetry getTelemetryById(@PathVariable final String id) {
		return this.telemetryService.getAllRoverTelemetry();
	}


	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/{roverID}/command-control")
	public ResponseEntity<String> doCommandControl(@RequestBody final CommandTelegram commandTelegram,
			@PathVariable final String roverID) {
		log.info("doCommand " + commandTelegram.getCommand() + " - " + commandTelegram.getSpeed() + " " + roverID);

		this.honoService.sendCommand(roverID, commandTelegram);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

}