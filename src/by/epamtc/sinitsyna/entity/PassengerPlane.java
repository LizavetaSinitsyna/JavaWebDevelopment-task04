/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.entity;

import java.time.LocalDate;

public class PassengerPlane extends Aircraft {

	private static final long serialVersionUID = 1L;

	public PassengerPlane() {
	}

	public PassengerPlane(String type) {
		super(type);
	}

	public PassengerPlane(String type, int fuelUsagePerKm, int tankCapacity, int currentFuelAmount, int loadCapacity,
			int maxSpeed, int bladesAmount, int crewAmount, int maxPassengersAmount, LocalDate inserviceDate) {
		super(type, fuelUsagePerKm, tankCapacity, currentFuelAmount, loadCapacity, maxSpeed, crewAmount,
				maxPassengersAmount, inserviceDate);
	}

}
