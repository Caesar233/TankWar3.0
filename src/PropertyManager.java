import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	static Properties props = new Properties();
	
	static {
		try {
			props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private PropertyManager() {}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
