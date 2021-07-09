package by.epamtc.sinitsyna.dao;

import java.io.File;

import by.epamtc.sinitsyna.validation.ValidationHelper;

public class FileProvider {
	private File file = new File("D:\\JAVA LEARNING\\epam\\JWD\\tasks\\aircompany.txt");

	private FileProvider() {
	}

	private static class ProviderHelper {
		private static final FileProvider INSTANCE = new FileProvider();
	}

	public static FileProvider getInstance() {
		return ProviderHelper.INSTANCE;
	}

	public File getFile() {
		return file;
	}

	public boolean setFile(File file) {
		if (ValidationHelper.isNull(file)) {
			return false;
		}
		this.file = file;
		return true;

	}

}
