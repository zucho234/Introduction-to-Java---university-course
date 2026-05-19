package i2jp.oop;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupRegistry {
	private static final Logger log = LogManager.getLogger(GroupRegistry.class);
	
	private static final Map<String, String> REG = new HashMap<>();
	
	public static boolean isAssigned(String id) {
		return REG.containsKey(id);
	}
	
	public static String assignedGroup(String id) {
		return REG.get(id);
	}
	
	public static void assign(String id, String groupName) {
	    REG.put(id, groupName);
	    log.info("Assigned personId={} to group={}", id, groupName);
	}

	public static void unassign(String id) {
		if (REG.remove(id) != null) {
		      log.debug("Unassigned personId={} from registry", id);
		    } else {
		      log.warn("Attempt to unassign non-registered personId={}", id);
		    }
	}

}
