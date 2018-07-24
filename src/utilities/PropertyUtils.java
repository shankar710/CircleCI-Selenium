package utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
	
	Properties prop = new Properties();
	
	public PropertyUtils(String filename) {
		try {
			InputStream input = null;
			input = new FileInputStream(filename);
			prop.load(input);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : "+e);
		}
		
	}
	
	public String getpropertyvalue(String propertkey){
		String propertyVal = null;
		propertyVal=prop.getProperty(propertkey);
		return propertyVal;
		
	}
	public int propertsize() {
		return prop.size();
	}

}
