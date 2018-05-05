package ch.vii.i18n.convert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

public class I18nPropertiesFromJson implements I18nProperties {

	@Override
	public Properties read(File f) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Properties readValue = objectMapper.readValue(new InputStreamReader(new FileInputStream(f), "UTF-8"),
					Properties.class);
			return readValue;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(Properties p, File f) {
		try {
			FileOutputStream outputStream = new FileOutputStream(f);
			p.store(new OutputStreamWriter(outputStream, Charset.forName("UTF8")), null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getFileExtension() {
		return ".properties";
	}

}
