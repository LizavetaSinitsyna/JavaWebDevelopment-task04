/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.logic.comparator;

import java.time.LocalDate;
import java.util.Comparator;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class InserviceDateAscendingComparator implements Comparator<Aircraft> {

	@Override
	public int compare(Aircraft aircraft1, Aircraft aircraft2) {
		if (ValidationHelper.isNull(aircraft1) || ValidationHelper.isNull(aircraft2)) {
			return 0;
		}
		LocalDate inserviceDate1 = aircraft1.getInserviceDate();
		LocalDate inserviceDate2 = aircraft2.getInserviceDate();
		if (ValidationHelper.isNull(inserviceDate1) || ValidationHelper.isNull(inserviceDate2)) {
			return 0;
		}
		return inserviceDate1.compareTo(inserviceDate2);
	}

}
