package ch.vii.i18n;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static java.util.Arrays.stream;

@Mojo(name = "properties2json", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class JsonToProperties extends AbstractMojo {

	@Parameter(property = "sourcePath", defaultValue = "${basedir}/src/properties")
	private String sourcePath;

	@Parameter(property = "targetPath", defaultValue = "${project.build.directory}/properties")
	private String targetPath;

	@Parameter(property = "fileWilcard", defaultValue = "*.properties")
	private String fileWilcard;

	@Override
	public void execute() throws MojoExecutionException {

		getLog().info("- Maven plugin : Properties to JSON or JSON to Properties");

		File srcPath = new File(sourcePath);
		if (!srcPath.isDirectory()) {
			throw new MojoExecutionException("SourcePath " + srcPath.getAbsolutePath() + " is not a directory !");
		}

		File dstPath = new File(targetPath);
		if (!dstPath.exists()) {
			dstPath.mkdirs();
		} else if (!dstPath.isDirectory()) {
			throw new MojoExecutionException("TargetPath " + targetPath + " is not a directory !");
		}

		FileFilter fileFilter = new WildcardFileFilter(fileWilcard);
		File[] listOfFiles = srcPath.listFiles(fileFilter);
		getLog().info("- Found files: " + listOfFiles.length + " in " + srcPath.getAbsolutePath() + "/" + fileWilcard);

		I18nProperties i18nProperties;
		if (fileWilcard.equalsIgnoreCase("*.properties")) {
			i18nProperties = new I18nPropertiesToJson();
		} else {
			i18nProperties = new I18nPropertiesFromJson();
		}

		stream(listOfFiles).forEach(f -> {
			File targetFile = renameFile(f.getAbsolutePath(), fileWilcard, i18nProperties.getFileExtension());

			getLog().info("--- from: " + f.getAbsolutePath());
			getLog().info("---   to: " + targetFile.getAbsolutePath());

			Properties properties = i18nProperties.read(f);
			i18nProperties.write(properties, targetFile);
		});

	}

	private File renameFile(String fileName, String fromExtension, String toExtension) {
		return new File(fileName.substring(0, fileName.length() - fromExtension.length() + 1) + toExtension);
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public void setFileWilcard(String fileWilcard) {
		this.fileWilcard = fileWilcard;
	}

}
