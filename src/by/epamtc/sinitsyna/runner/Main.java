/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.runner;

import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.AirCompanyLogic;
import by.epamtc.sinitsyna.logic.FileProvider;
import by.epamtc.sinitsyna.logic.ServiceException;
import by.epamtc.sinitsyna.logic.ServiceProvider;
import by.epamtc.sinitsyna.logic.comparator.LoadCapacityDescendingComparator;

import java.io.File;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		AirCompanyLogic logic = ServiceProvider.getInstance().getLogic();
		FileProvider provider = FileProvider.getInstance();
		List<Aircraft> result;

		AirCompany company = new AirCompany();
		company.setLegalAddress("street");

		company.addAircraft(new Aircraft());
		company.addAircraft(new Aircraft());
		company.addAircraft(new Helicopter());

		try {
			logic.save(company);
			provider.setFile(new File(
					".\\AirCompanyFiles\\AirCompanyForRead.txt"));
			company = logic.read();
			System.out.println("GeneralLoadCapacity = " + logic.retrieveGeneralLoadCapacity(company));
			System.out.println("GeneralPassengersCapacity = " + logic.retrieveGeneralPassengersCapacity(company));
			result = logic.retrieveAircraftsByFuelUsage(company, 30, 2500);
			System.out.println("Aircrafts with specified fuel usage [30; 2500] > ");
			if (result.isEmpty()) {
				System.out.println("No such aircrafts.");
			} else {
				for (Aircraft aircraft : result) {
					System.out.println(aircraft);
				}
			}
			logic.sort(company, new LoadCapacityDescendingComparator());
			provider.setFile(new File(
					".\\AirCompanyFiles\\AirCompanyAfterSorting.txt"));
			logic.save(company);
			provider.setFile(new File(
					".\\AirCompanyFiles\\AirCompany.txt"));
			logic.save(company);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
