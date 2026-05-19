package i2jp.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoLoggingXml {
	private static final Logger log = LogManager.getLogger(DemoLoggingXml.class);

	public static void main(String[] args) {
		log.info("Log4j2 configured using log4j2.xml");
	    log.debug("This DEBUG message will be printed only if level <= DEBUG");
	}
}