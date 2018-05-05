package ch.vii.i18n;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import ch.vii.i18n.convert.I18nProperties;

public abstract class AbstractJson2PropertiesMojo extends AbstractMojo {

	@Parameter(property = "sourcePath", defaultValue = "${basedir}/src/properties")
	private String sourcePath;

	@Parameter(property = "targetPath", defaultValue = "${project.build.directory}/properties")
	private String targetPath;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		getLog().info("- Properties 2 JSON or JSON 2 Properties \n\tsourcePath: " + sourcePath + "\n\ttargetPath: "
				+ targetPath + "\n\tfileWilcard: " + getFileWildcard());
		File srcPath = new File(sourcePath);
		if (!srcPath.exists()) {
			getLog().info("-- Source folder does not exist: " + srcPath.getAbsolutePath());
			return;
		}
		if (!srcPath.isDirectory()) {
			throw new MojoExecutionException("SourcePath " + srcPath.getAbsolutePath() + " is not a directory !");
		}

		File dstPath = new File(targetPath);
		if (!dstPath.exists()) {
			dstPath.mkdirs();
		} else if (!dstPath.isDirectory()) {
			throw new MojoExecutionException("TargetPath " + targetPath + " is not a directory !");
		}

		FileFilter fileFilter = new WildcardFileFilter(getFileWildcard());
		File[] listOfFiles = srcPath.listFiles(fileFilter);
		getLog().info(
				"- Found files: " + listOfFiles.length + " in " + srcPath.getAbsolutePath() + "/" + getFileWildcard());

		I18nProperties i18nProperties = getI18nProperties();

		// Arrays.stream(listOfFiles).forEach(f -> {
		// lampda not supported
		// });
		for (File f : listOfFiles) {
			File targetFile = renameFile(targetPath + "/" + f.getName(), getFileWildcard(),
					i18nProperties.getFileExtension());

			getLog().info("--- from: " + f.getAbsolutePath());
			getLog().info("--- to: " + targetFile.getAbsolutePath());

			Properties properties = i18nProperties.read(f);
			i18nProperties.write(properties, targetFile);
		}

	}

	abstract I18nProperties getI18nProperties();

	abstract String getFileWildcard();

	private File renameFile(String fileName, String fromExtension, String toExtension) {
		return new File(fileName.substring(0, fileName.length() - fromExtension.length() + 1) + toExtension);
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

}
