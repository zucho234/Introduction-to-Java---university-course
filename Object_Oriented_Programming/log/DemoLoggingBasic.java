package i2jp.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoLoggingBasic {
	public static final Logger log = LogManager.getLogger(DemoLoggingBasic.class);

	public static void main(String[] args) {
		log.trace("TRACE message (very detailed)");
	    log.debug("DEBUG message (for developers)");
	    log.info("INFO message (high level information)");
	    log.warn("WARN message (something looks strange)");
	    log.error("ERROR message (operation failed)");
	    log.fatal("FATAL message (application about to crash)");
	}
}
