package by.epamtc.sinitsyna.logic;

import java.io.File;

import by.epamtc.sinitsyna.validation.ValidationHelper;

public class FileProvider {
	private File file = new File(".\\AirCompanyFiles\\AirCompany.txt");

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
