/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.entity;

import java.time.LocalDate;

import by.epamtc.sinitsyna.validation.ValidationHelper;

public class Helicopter extends Aircraft implements Cloneable {
	private static final long serialVersionUID = 1L;

	private int bladesAmount;

	public Helicopter() {
	}

	public Helicopter(String type) {
		super(type);
	}

	public Helicopter(String type, int fuelUsagePerKm, int tankCapacity, int currentFuelAmount, int loadCapacity,
			int maxSpeed, int bladesAmount, int crewAmount, int maxPassengersAmount, LocalDate inserviceDate) {
		super(type, fuelUsagePerKm, tankCapacity, currentFuelAmount, loadCapacity, maxSpeed, crewAmount,
				maxPassengersAmount, inserviceDate);
		this.bladesAmount = bladesAmount;
	}

	public int getBladesAmount() {
		return bladesAmount;
	}

	public boolean setBladesAmount(int bladesAmount) {
		if (ValidationHelper.isNegative(bladesAmount)) {
			return false;
		}
		this.bladesAmount = bladesAmount;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bladesAmount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Helicopter other = (Helicopter) obj;
		if (bladesAmount != other.bladesAmount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [bladesAmount=" + bladesAmount + "]";
	}

}
