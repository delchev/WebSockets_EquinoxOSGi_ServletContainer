/*******************************************************************************
 * Copyright (c) 2016 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 *******************************************************************************/

package osgi.websockets.runtime.metrics;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import osgi.websockets.bridge.OSGiBridge;
import osgi.websockets.runtime.log.WebSocketLogBridgeServletInternal;

public class MetricsActivator implements BundleActivator {

	private static final Logger logger = Logger.getLogger(MetricsActivator.class.getCanonicalName());

	public static final WebSocketLogBridgeServletInternal webSocketLogBridgeServletInternal = new WebSocketLogBridgeServletInternal();

	@Override
	public void start(BundleContext context) throws Exception {
		setupLogChannel();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		webSocketLogBridgeServletInternal.closeAll();
	}

	protected void setupLogChannel() {

		logger.finer("Setting log channel internal ...");

		OSGiBridge.BRIDGES.put("websocket_log_channel_internal", webSocketLogBridgeServletInternal);

		logger.finer("Log channel internal has been set.");

	}

}
