package ch.vii.i18n.convert;

import java.io.File;
import java.util.Properties;

public interface I18nProperties {

	String getFileExtension();

	Properties read(File f);

	void write(Properties p, File f);

}
