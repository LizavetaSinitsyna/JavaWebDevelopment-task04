/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.logic.comparator;

import java.util.Comparator;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class FuelUsageDescendingComparator implements Comparator<Aircraft> {

	@Override
	public int compare(Aircraft aircraft1, Aircraft aircraft2) {
		if(ValidationHelper.isNull(aircraft1) || ValidationHelper.isNull(aircraft2)) {
			return 0;
		}
		int fuelUsagePerKm1 = aircraft1.getFuelUsagePerHour();
		int fuelUsagePerKm2 = aircraft2.getFuelUsagePerHour();
		if (fuelUsagePerKm1 > fuelUsagePerKm2) {
			return -1;
		} else if (fuelUsagePerKm1 < fuelUsagePerKm2) {
			return 1;
		} else {
			return new InserviceDateDescendingComparator().compare(aircraft1, aircraft2);

		}

	}
}