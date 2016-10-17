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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemoryServlet
 */
public class StandardServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(StandardServlet.class.getCanonicalName());

	private static final long serialVersionUID = 5645919875259516138L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StandardServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		try {
			String greetings = "Hello from OSGi!";
			MetricsActivator.webSocketLogBridgeServletInternal.log(greetings);
			writer.println(greetings + " sent via WebSocket");
		} catch (Exception e) {
			logger.throwing(StandardServlet.class.getCanonicalName(), "doGet", e);
			throw new ServletException(e);
		}
		writer.flush();
		writer.close();
	}

}
