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
package com.appstacle.telemetryui.cloud_connection;

public class Command {
	private final String command;
	private final String payload;

	public Command(final String command, final String payload) {
		this.command = command;
		this.payload = payload;
	}

	public String getCommand() {
		return command;
	}

	public String getPayload() {
		return payload;
	}

}
