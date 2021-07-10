/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.entity;

import java.io.Serializable;
import java.time.LocalDate;

import by.epamtc.sinitsyna.validation.ValidationHelper;

public class Aircraft implements FlyingMachine, Serializable {
	private static final long serialVersionUID = 1L;

	private String type;
	private int fuelUsagePerHour;
	private int tankCapacity;
	private int currentFuelAmount;
	private int loadCapacity;
	private int maxSpeed;
	private int crewAmount;
	private int maxPassengersAmount;
	private int maxUnitLoadDeviceAmount;
	private LocalDate inserviceDate;

	public Aircraft() {
	}

	public Aircraft(String type) {
		this.type = type;
	}

	public Aircraft(String type, int fuelUsagePerHour, int tankCapacity, int currentFuelAmount, int loadCapacity,
			int maxSpeed, int crewAmount, int maxPassengersAmount, int maxUnitLoadDeviceAmount,
			LocalDate inserviceDate) {
		this.type = type;
		this.fuelUsagePerHour = fuelUsagePerHour;
		this.tankCapacity = tankCapacity;
		this.currentFuelAmount = currentFuelAmount;
		this.loadCapacity = loadCapacity;
		this.maxSpeed = maxSpeed;
		this.crewAmount = crewAmount;
		this.maxPassengersAmount = maxPassengersAmount;
		this.maxUnitLoadDeviceAmount = maxUnitLoadDeviceAmount;
		this.inserviceDate = inserviceDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFuelUsagePerHour() {
		return fuelUsagePerHour;
	}

	public void setFuelUsagePerHour(int fuelUsagePerHour) {
		this.fuelUsagePerHour = fuelUsagePerHour;
	}

	public int getTankCapacity() {
		return tankCapacity;
	}

	public void setTankCapacity(int tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

	public int getCurrentFuelAmount() {
		return currentFuelAmount;
	}

	public void setCurrentFuelAmount(int currentFuelAmount) {
		this.currentFuelAmount = currentFuelAmount;
	}

	public boolean addFuelAmount(int fuelToAdd) {
		if (ValidationHelper.isNegative(fuelToAdd) || currentFuelAmount > tankCapacity - fuelToAdd) {
			return false;
		}
		this.currentFuelAmount += fuelToAdd;
		return true;
	}

	@Override
	public int getLoadCapacity() {
		return loadCapacity;
	}

	public void setLoadCapacity(int loadCapacity) {
		this.loadCapacity = loadCapacity;
	}

	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getCrewAmount() {
		return crewAmount;
	}

	public void setCrewAmount(int crewAmount) {
		this.crewAmount = crewAmount;
	}

	public int getMaxPassengersAmount() {
		return maxPassengersAmount;
	}

	public void setMaxPassengersAmount(int maxPassengersAmount) {
		this.maxPassengersAmount = maxPassengersAmount;
	}

	public int getMaxUnitLoadDeviceAmount() {
		return maxUnitLoadDeviceAmount;
	}

	public void setMaxUnitLoadDeviceAmount(int maxUnitLoadDeviceAmount) {
		this.maxUnitLoadDeviceAmount = maxUnitLoadDeviceAmount;
	}

	public LocalDate getInserviceDate() {
		return inserviceDate;
	}

	public boolean setInserviceDate(LocalDate inserviceDate) {
		if (ValidationHelper.isNull(this.inserviceDate)) {
			this.inserviceDate = inserviceDate;
			return true;
		}
		return false;
	}

	@Override
	public boolean executeFly(int distance) {
		int requiredFuelAmount = distance * fuelUsagePerHour;
		if (requiredFuelAmount < currentFuelAmount) {
			return false;
		}
		currentFuelAmount -= requiredFuelAmount;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + crewAmount;
		result = prime * result + currentFuelAmount;
		result = prime * result + fuelUsagePerHour;
		result = prime * result + ((inserviceDate == null) ? 0 : inserviceDate.hashCode());
		result = prime * result + loadCapacity;
		result = prime * result + maxPassengersAmount;
		result = prime * result + maxSpeed;
		result = prime * result + maxUnitLoadDeviceAmount;
		result = prime * result + tankCapacity;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aircraft other = (Aircraft) obj;
		if (crewAmount != other.crewAmount)
			return false;
		if (currentFuelAmount != other.currentFuelAmount)
			return false;
		if (fuelUsagePerHour != other.fuelUsagePerHour)
			return false;
		if (inserviceDate == null) {
			if (other.inserviceDate != null)
				return false;
		} else if (!inserviceDate.equals(other.inserviceDate))
			return false;
		if (loadCapacity != other.loadCapacity)
			return false;
		if (maxPassengersAmount != other.maxPassengersAmount)
			return false;
		if (maxSpeed != other.maxSpeed)
			return false;
		if (maxUnitLoadDeviceAmount != other.maxUnitLoadDeviceAmount)
			return false;
		if (tankCapacity != other.tankCapacity)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [type=" + type + ", fuelUsagePerHour=" + fuelUsagePerHour + ", tankCapacity="
				+ tankCapacity + ", currentFuelAmount=" + currentFuelAmount + ", loadCapacity=" + loadCapacity
				+ ", maxSpeed=" + maxSpeed + ", crewAmount=" + crewAmount + ", maxPassengersAmount="
				+ maxPassengersAmount + ", maxUnitLoadDeviceAmount=" + maxUnitLoadDeviceAmount + ", inserviceDate="
				+ inserviceDate + "]";
	}

}