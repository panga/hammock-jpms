open module hammock.jpms {

	requires cdi.api;
	requires hammock.core;
	requires java.json.bind;
	requires java.ws.rs;
	requires java.xml;
	requires jdk.unsupported;
	requires org.apache.logging.log4j;

}

