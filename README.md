# WebSockets in Equinox OSGi in a Servlet Container
This repository contains the sample application presented at EclipseCon EU 2016

To run this sample:

1. Clone the sources
2. Build (you need JDK8.x and Apache Maven)
  mvn clean install
3. Install Tomcat 8
4. Copy the built dist/tomcat/allinone/ROOT.war to the $CATALINA_HOME/webapps
5. Add an user in the $CATALINA_HOME/conf/tomcat-users.xml with the role 'Everyone'
6. Start Tomcat
7. Access and Login to http://localhost:8080/services/standard
8. Create WS Client in Chrome DevTools
  var logSocket = new WebSocket("ws://localhost:8080/log");
9. Inspect Frames in Network/WS
10. Access and Login from a second browser to http://localhost:8080/services/standard. You should see the "Hello from OSGi!" message in the frames tab
11. Send a message from WS client and receive the echo
  logSocket.send("Hello from Client!");

###Enjoy
