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
import by.epamtc.sinitsyna.logic.validator.AirCompanyValidator;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AirCompanyLogic {
	private DAOProvider daoProvider = DAOProvider.getInstance();

	public AirCompany read() throws ServiceException {
		AirCompany company = null;
		AirCompanyValidator validator = new AirCompanyValidator();
		try {
			company = daoProvider.getLegalEntityDAO().read();

			validator.validate(company);

		} catch (DAOException | InvalidAirCompanyException e) { 
			throw new ServiceException(e.getMessage(), e);
		}

		return company;
	}

	public BigInteger retrieveGeneralLoadCapacity(AirCompany company) {
		BigInteger result = new BigInteger("0");
		if (ValidationHelper.isNull(company)) {
			return result;
		}

		Aircraft aircraft;

		Iterator<Aircraft> iterator = company.getAircraftsIterator();

		while (iterator.hasNext()) {
			aircraft = iterator.next();
			result = result.add(new BigInteger(Integer.toString(aircraft.getLoadCapacity())));
		}

		return result;
	}

	public BigInteger retrieveGeneralPassengersCapacity(AirCompany company) {
		BigInteger result = new BigInteger("0");
		if (ValidationHelper.isNull(company)) {
			return result;
		}

		Aircraft aircraft;

		Iterator<Aircraft> iterator = company.getAircraftsIterator();

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
		int size = company.retrieveAircraftsAmount();
		for (int i = 0; i < size - 1; ++i) {
			for (int j = 0; j < size - i - 1; ++j) {
				try {
					if (comparator.compare(company.get(j), company.get(j + 1)) == 1) {
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

	public List<Aircraft> retrieveAircraftsByFuelUsage(AirCompany company, int bound1, int bound2) {
		List<Aircraft> result = new ArrayList<Aircraft>();
		if (ValidationHelper.isNull(company)) {
			return result;
		}

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
			fuelUsagePerKm = aircraft.getFuelUsagePerKm();

			if (fuelUsagePerKm >= bound1 && fuelUsagePerKm <= bound2)
				result.add(aircraft);
		}

		return result;
	}

}
