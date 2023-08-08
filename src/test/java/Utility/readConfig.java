package Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class readConfig {
	static Properties prop;
	
	public readConfig() throws Exception{
		FileInputStream fis = new FileInputStream(".//config.properties");
		
		prop = new Properties();
		prop.load(fis);
		
		
	}
	
	public static String getApp() {

		return prop.getProperty("nop_appURL");
	}

	public static String getUserName() {

		return prop.getProperty("userName");
	}

	public static String getPassword() {

		return prop.getProperty("password");
	}

}

