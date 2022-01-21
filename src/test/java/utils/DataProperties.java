package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DataProperties {

	private static Properties props = new Properties();

	private Path dataPath = Paths.get("src/test/resources/testdata.properties");

	public DataProperties() {
		props = new Properties();
		loadData();
	}

	private Properties loadData() {
		try {
			props.load(new FileInputStream(dataPath.toFile()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public String getUserName() {
		return props.getProperty("user");
	}

	public String getPassword() {
		return props.getProperty("password");
	}

	public String getBrowser() {
		return props.getProperty("browser");
	}
}
