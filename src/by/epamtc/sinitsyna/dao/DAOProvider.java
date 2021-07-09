package by.epamtc.sinitsyna.dao;

import by.epamtc.sinitsyna.dao.impl.FileAirСompanyDAO;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public final class DAOProvider {
	private AirCompanyDAO airCompanyDAO = new FileAirСompanyDAO();

	private DAOProvider() {
	}

	private static class ProviderHelper {
		private static final DAOProvider INSTANCE = new DAOProvider();
	}

	public static DAOProvider getInstance() {
		return ProviderHelper.INSTANCE;
	}

	public AirCompanyDAO getLegalEntityDAO() {
		return airCompanyDAO;
	}

	public boolean setLegalEntityDAO(AirCompanyDAO airCompanyDAO) {
		if (ValidationHelper.isNull(airCompanyDAO)) {
			return false;
		}
		this.airCompanyDAO = airCompanyDAO;
		return true;
	}

}
