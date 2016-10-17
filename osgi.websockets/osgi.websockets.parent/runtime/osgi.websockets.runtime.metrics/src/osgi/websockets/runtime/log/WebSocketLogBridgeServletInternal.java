/*******************************************************************************
 * Copyright (c) 2016 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * SAP - initial API and implementation
 *******************************************************************************/

package osgi.websockets.runtime.log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public class WebSocketLogBridgeServletInternal {

	private static final Logger logger = Logger.getLogger(WebSocketLogBridgeServletInternal.class.getCanonicalName());

	private static Map<String, Session> openSessions = new ConcurrentHashMap<String, Session>();

	@OnOpen
	public void onOpen(Session session) throws IOException {
		openSessions.put(session.getId(), session);
		session.getBasicRemote().sendText("[log] open: " + session.getId());
		logger.finer("[ws:log] onOpen: " + session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		logger.finer("[ws:log] onMessage: " + message);
		echo(message, session);
	}

	@OnError
	public void onError(Session session, String error) {
		logger.finer("[ws:log] onError: " + error);
	}

	@OnClose
	public void onClose(Session session) {
		openSessions.remove(session.getId());
		logger.finer("[ws:log] onClose: Session " + session.getId() + " has ended");
	}

	public static void sendText(String sessionId, String message) {
		try {
			if (sessionId == null) {
				for (Object element : openSessions.values()) {
					Session session = (Session) element;
					session.getBasicRemote().sendText(message);
				}
			} else {
				openSessions.get(sessionId).getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			logger.throwing(WebSocketLogBridgeServletInternal.class.getCanonicalName(), e.getMessage(), e);
		}
	}

	public void log(String message) {
		for (Session session : openSessions.values()) {
			try {
				synchronized (session) {
					session.getBasicRemote().sendText(String.format("[log] %s", message));
				}
			} catch (Throwable e) {
				// do not log it with the Logger
				e.printStackTrace();
			}
		}
	}

	public void echo(String message, Session session) {
		try {
			synchronized (session) {
				session.getBasicRemote().sendText(String.format("[echo] %s", message));
			}
		} catch (Throwable e) {
			// do not log it with the Logger
			e.printStackTrace();
		}
	}

	public void closeAll() {
		for (Session session : openSessions.values()) {
			try {
				synchronized (session) {
					session.close();
				}
			} catch (Throwable e) {
				// do not log it with the Logger
				e.printStackTrace();
			}
		}
	}

}
