package ch.vii.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import com.fasterxml.jackson.databind.ObjectMapper;

@Mojo(name = "json2properties", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class Json2PropertiesMojo extends AbstractJson2PropertiesMojo {

	@Override
	String getFileWildcard() {
		return "*.json";
	}

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
