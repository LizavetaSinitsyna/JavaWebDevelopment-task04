/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.runner;

import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.AirCompanyLogic;
import by.epamtc.sinitsyna.logic.ServiceException;

import java.io.File;

import by.epamtc.sinitsyna.dao.DAOException;
import by.epamtc.sinitsyna.dao.FileProvider;
import by.epamtc.sinitsyna.dao.impl.FileAirСompanyDAO;

public class Main {
	public static void main(String[] args) {
		/*FileAirСompanyDAO fileAirCompanyDAO = new FileAirСompanyDAO();
		FileProvider provider = FileProvider.getInstance();

		AirCompany company = new AirCompany();
		company.setLegalAddress("street");

		company.addAircraft(new Aircraft());
		company.addAircraft(new Aircraft());
		company.addAircraft(new Helicopter());

		try {
			//fileAirCompanyDAO.save(company);
			company = fileAirCompanyDAO.read();
			provider.setFile(new File("D:\\JAVA LEARNING\\epam\\JWD\\tasks\\aircompanyREAD.txt"));
			fileAirCompanyDAO.save(company);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		AirCompanyLogic logic = new AirCompanyLogic();
		try {
			AirCompany company = logic.read();
			System.out.println(logic.retrieveGeneralPassengersCapacity(company));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
