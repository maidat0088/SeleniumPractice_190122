package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadProperties {

	Properties prop = new Properties();

	Path dataPath = Paths.get("src/test/resources/testdata.properties");

	public Properties getData() throws IOException {
		prop.load(new FileInputStream(dataPath.toFile()));
		return prop;
	}

}
