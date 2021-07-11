/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import by.epamtc.sinitsyna.dao.DAOException;
import by.epamtc.sinitsyna.dao.DAOProvider;
import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.exception.IndexOutOfBoundsException;
import by.epamtc.sinitsyna.logic.exception.InvalidAirCompanyException;
import by.epamtc.sinitsyna.logic.validator.AirCompanyValidator;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AirCompanyLogic {
	private static final String NULL_AIR_COMPANY_EXCEPTION_MESSAGE = "AirCompany can't be null.";
	private DAOProvider daoProvider = DAOProvider.getInstance();

	public AirCompany read() throws ServiceException {
		AirCompany company = null;
		AirCompanyValidator validator = new AirCompanyValidator();
		try {
			company = daoProvider.getAirCompanyDAO().read();

			if (!validator.isValid(company)) {
				throw new ServiceException(new InvalidAirCompanyException(validator.getExceptionMessage()));
			}

		} catch (DAOException | InvalidAirCompanyException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return company;
	}

	public void save(AirCompany company) throws ServiceException {
		try {
			daoProvider.getAirCompanyDAO().save(company);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public BigInteger retrieveGeneralLoadCapacity(AirCompany company) throws ServiceException {
		if (ValidationHelper.isNull(company)) {
			throw new ServiceException(NULL_AIR_COMPANY_EXCEPTION_MESSAGE);
		}

		Aircraft aircraft;
		Iterator<Aircraft> iterator = company.getAircraftsIterator();
		BigInteger result = new BigInteger("0");

		while (iterator.hasNext()) {
			aircraft = iterator.next();
			result = result.add(new BigInteger(Integer.toString(aircraft.getLoadCapacity())));
		}

		return result;
	}

	public BigInteger retrieveGeneralPassengersCapacity(AirCompany company) throws ServiceException {
		if (ValidationHelper.isNull(company)) {
			throw new ServiceException(NULL_AIR_COMPANY_EXCEPTION_MESSAGE);
		}

		Aircraft aircraft;
		Iterator<Aircraft> iterator = company.getAircraftsIterator();
		BigInteger result = new BigInteger("0");

		while (iterator.hasNext()) {
			aircraft = iterator.next();
			result = result.add(new BigInteger(Integer.toString(aircraft.getMaxPassengersAmount())));
		}

		return result;
	}

	public void sort(AirCompany company, Comparator<Aircraft> comparator) {
		if (ValidationHelper.isNull(company) || ValidationHelper.isNull(comparator)) {
			return;
		}
		int size = company.retrieveAircraftsCount();
		for (int i = 0; i < size - 1; ++i) {
			for (int j = 0; j < size - i - 1; ++j) {
				try {
					if (comparator.compare(company.get(j), company.get(j + 1)) > 0) {
						swap(company, j, j + 1);
					}
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void swap(AirCompany company, int index1, int index2) {
		Aircraft temp;
		try {
			temp = company.get(index1);
			company.set(index1, company.get(index2));
			company.set(index2, temp);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public List<Aircraft> retrieveAircraftsByFuelUsage(AirCompany company, int bound1, int bound2)
			throws ServiceException {
		if (ValidationHelper.isNull(company)) {
			throw new ServiceException(NULL_AIR_COMPANY_EXCEPTION_MESSAGE);
		}

		List<Aircraft> result = new ArrayList<Aircraft>();
		Aircraft aircraft;
		int fuelUsagePerKm;

		Iterator<Aircraft> iterator = company.getAircraftsIterator();

		if (bound1 > bound2) {
			int temp = bound1;
			bound1 = bound2;
			bound2 = temp;
		}

		while (iterator.hasNext()) {
			aircraft = iterator.next();
			fuelUsagePerKm = aircraft.getFuelUsagePerHour();

			if (fuelUsagePerKm >= bound1 && fuelUsagePerKm <= bound2)
				result.add(aircraft);
		}

		return result;
	}

}
