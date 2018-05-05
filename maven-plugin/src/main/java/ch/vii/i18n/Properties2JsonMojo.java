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

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Mojo(name = "properties2json", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class Properties2JsonMojo extends AbstractJson2PropertiesMojo {

	@Override
	public Properties read(File f) {
		try {
			Properties readValue = new Properties();
			readValue.load(new InputStreamReader(new FileInputStream(f), "UTF-8"));

			return readValue;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(Properties p, File f) {
		try {

			ObjectMapper mapper = new ObjectMapper();

			// Object to JSON in file
			mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
			mapper.writerWithDefaultPrettyPrinter()
					.writeValue(new OutputStreamWriter(new FileOutputStream(f), Charset.forName("UTF8")), p);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getFileExtension() {
		return ".json";
	}

	@Override
	String getFileWildcard() {
		return "*.properties";
	}

}
