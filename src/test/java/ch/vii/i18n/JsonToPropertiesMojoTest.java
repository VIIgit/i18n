package ch.vii.i18n;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

import ch.vii.i18n.JsonToProperties;

public class JsonToPropertiesTest {

	@Test
	public void testFromJsonToProperties() {
		JsonToProperties jsonToProperties = new JsonToProperties();
		jsonToProperties.setSourcePath("./src/main/resources/fromJson");
		jsonToProperties.setTargetPath("./target/main/resources/fromJson");
		jsonToProperties.setFileWilcard("*.json");
		try {
			jsonToProperties.execute();
		} catch (MojoExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFromPropertiesToJson() {
		JsonToProperties jsonToProperties = new JsonToProperties();
		jsonToProperties.setSourcePath("./src/main/resources/fromProperties");
		jsonToProperties.setTargetPath("./target/main/resources/fromProperties");
		jsonToProperties.setFileWilcard("*.properties");
		try {
			jsonToProperties.execute();
		} catch (MojoExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
