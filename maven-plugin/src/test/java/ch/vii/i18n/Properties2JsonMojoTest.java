package ch.vii.i18n;

import org.junit.Test;

public class Properties2JsonMojoTest {

	@Test
	public void testFromJsonToProperties() {
		Json2PropertiesMojo jsonToProperties = new Json2PropertiesMojo();
		jsonToProperties.setSourcePath("./src/test/resources/fromJson");
		jsonToProperties.setTargetPath("./target/test/resources/fromJson");
		try {
			jsonToProperties.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFromPropertiesToJson() {
		Properties2JsonMojo jsonToProperties = new Properties2JsonMojo();
		jsonToProperties.setSourcePath("./src/test/resources/fromProperties");
		jsonToProperties.setTargetPath("./target/test/resources/fromProperties");
		try {
			jsonToProperties.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
