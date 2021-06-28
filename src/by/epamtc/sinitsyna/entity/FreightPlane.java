/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.entity;

import java.time.LocalDate;

import by.epamtc.sinitsyna.validation.ValidationHelper;

public class FreightPlane extends Aircraft implements Cloneable {
	private static final long serialVersionUID = 1L;

	private int maxUnitLoadDeviceAmount;

	public FreightPlane() {
	}

	public FreightPlane(String type) {
		super(type);
	}

	public FreightPlane(String type, int fuelUsagePerKm, int tankCapacity, int currentFuelAmount, int loadCapacity,
			int maxSpeed, int maxUnitLoadDeviceAmount, int crewAmount, int maxPassengersAmount,
			LocalDate inserviceDate) {
		super(type, fuelUsagePerKm, tankCapacity, currentFuelAmount, loadCapacity, maxSpeed, crewAmount,
				maxPassengersAmount, inserviceDate);
		this.maxUnitLoadDeviceAmount = maxUnitLoadDeviceAmount;
	}

	public int getMaxUnitLoadDeviceAmount() {
		return maxUnitLoadDeviceAmount;
	}

	public boolean setMaxUnitLoadDeviceAmount(int maxUnitLoadDeviceAmount) {
		if (ValidationHelper.isNegative(maxUnitLoadDeviceAmount)) {
			return false;
		}
		this.maxUnitLoadDeviceAmount = maxUnitLoadDeviceAmount;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxUnitLoadDeviceAmount;
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
		FreightPlane other = (FreightPlane) obj;
		if (maxUnitLoadDeviceAmount != other.maxUnitLoadDeviceAmount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [maxUnitLoadDeviceAmount=" + maxUnitLoadDeviceAmount + "]";
	}

}
