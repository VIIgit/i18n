package ch.vii.i18n;

public class Main {

	public static void main(String[] args) {

		String sourcePath = null;
		String targetPat = null;

		for (int i = 0; i < args.length; i = i + 2) {
			String arg = args[i];
			if (arg.equals("sourcePath")) {
				sourcePath = args[i + 1];
			} else if (arg.equals("targetPat")) {
				targetPat = args[i + 1];
			}
		}

		Properties2JsonMojo jsonToProperties = new Properties2JsonMojo();
		jsonToProperties.setSourcePath(sourcePath);
		jsonToProperties.setTargetPath(targetPat);
		try {
			jsonToProperties.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
