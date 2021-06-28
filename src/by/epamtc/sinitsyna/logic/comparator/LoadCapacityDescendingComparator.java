/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.logic.comparator;

import java.util.Comparator;

import by.epamtc.sinitsyna.entity.Aircraft;

public class LoadCapacityDescendingComparator implements Comparator<Aircraft> {

	@Override
	public int compare(Aircraft aircraft1, Aircraft aircraft2) {
		int loadCapacity1 = aircraft1.getLoadCapacity();
		int loadCapacity2 = aircraft2.getLoadCapacity();
		if (loadCapacity1 > loadCapacity2) {
			return -1;
		} else if (loadCapacity1 < loadCapacity2) {
			return 1;
		} else {
			return new InserviceDateDescendingComparator().compare(aircraft1, aircraft2);
		}
	}

}