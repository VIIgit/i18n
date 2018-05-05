package ch.vii.i18n;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import ch.vii.i18n.convert.I18nProperties;
import ch.vii.i18n.convert.I18nPropertiesToJson;

@Mojo(name = "properties2json", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class Properties2JsonMojo extends AbstractJson2PropertiesMojo {

	private I18nProperties i18nProperties = new I18nPropertiesToJson();

	@Override
	I18nProperties getI18nProperties() {
		return i18nProperties;
	}

	@Override
	String getFileWildcard() {
		return "*.properties";
	}

}
