/*******************************************************************************
 * Copyright (c) 2016 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 *******************************************************************************/

package osgi.websockets.bridge;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.equinox.servletbridge.BridgeServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enhanced Bridge Servlet which retrieves specific parameters and objects
 * from the target environment and provide them to OSGi environment
 */
public class OSGiBridge extends BridgeServlet {

	private static final long serialVersionUID = -8043662807856187626L;

	private static final Logger logger = LoggerFactory.getLogger(OSGiBridge.class);

	public static Map<String, Object> BRIDGES = Collections.synchronizedMap(new HashMap<String, Object>());

}
