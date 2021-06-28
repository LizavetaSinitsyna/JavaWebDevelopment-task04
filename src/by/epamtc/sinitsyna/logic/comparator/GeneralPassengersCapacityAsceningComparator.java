/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.logic.comparator;

import java.util.Comparator;

import by.epamtc.sinitsyna.entity.Aircraft;

public class GeneralPassengersCapacityAsceningComparator implements Comparator<Aircraft> {

	@Override
	public int compare(Aircraft aircraft1, Aircraft aircraft2) {
		int passengers1 = aircraft1.getMaxPassengersAmount();
		int passengers2 = aircraft2.getMaxPassengersAmount();
		if (passengers1 > passengers2) {
			return 1;
		} else if (passengers1 < passengers2) {
			return -1;
		} else {
			return new InserviceDateAscendingComparator().compare(aircraft1, aircraft2);
		}
	}

}
