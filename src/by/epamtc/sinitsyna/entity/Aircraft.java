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
	private int fuelUsagePerKm;
	private int tankCapacity;
	private int currentFuelAmount;
	private int loadCapacity;
	private int maxSpeed;
	private int crewAmount;
	private int maxPassengersAmount;
	private LocalDate inserviceDate;

	public Aircraft() {
	}

	public Aircraft(String type) {
		this.type = type;
	}

	public Aircraft(String type, int fuelUsagePerKm, int tankCapacity, int currentFuelAmount, int loadCapacity,
			int maxSpeed, int crewAmount, int maxPassengersAmount, LocalDate inserviceDate) {
		this.type = type;
		this.fuelUsagePerKm = fuelUsagePerKm;
		this.tankCapacity = tankCapacity;
		this.currentFuelAmount = currentFuelAmount;
		this.loadCapacity = loadCapacity;
		this.maxSpeed = maxSpeed;
		this.crewAmount = crewAmount;
		this.maxPassengersAmount = maxPassengersAmount;
		this.inserviceDate = inserviceDate;
	}

	public String getType() {
		return type;
	}

	public boolean setType(String type) {
		if (type == null) {
			return false;
		}
		this.type = type;
		return true;
	}

	public int getFuelUsagePerKm() {
		return fuelUsagePerKm;
	}

	public boolean setFuelUsagePerKm(int fuelUsagePerKm) {
		if (ValidationHelper.isNegative(fuelUsagePerKm)) {
			return false;
		}
		this.fuelUsagePerKm = fuelUsagePerKm;
		return true;

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

	public boolean setCurrentFuelAmount(int currentFuelAmount) {
		if (ValidationHelper.isNegative(currentFuelAmount)) {
			return false;
		}
		this.currentFuelAmount = currentFuelAmount;
		return true;
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

	public boolean setLoadCapacity(int loadCapacity) {
		if (ValidationHelper.isNegative(loadCapacity)) {
			return false;
		}
		this.loadCapacity = loadCapacity;
		return true;
	}

	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}

	public boolean setMaxSpeed(int maxSpeed) {
		if (ValidationHelper.isNegative(maxSpeed)) {
			return false;
		}
		this.maxSpeed = maxSpeed;
		return true;
	}

	public int getCrewAmount() {
		return crewAmount;
	}

	public boolean setCrewAmount(int crewAmount) {
		if (ValidationHelper.isNegative(crewAmount)) {
			return false;
		}
		this.crewAmount = crewAmount;
		return true;
	}

	public int getMaxPassengersAmount() {
		return maxPassengersAmount;
	}

	public boolean setMaxPassengersAmount(int maxPassengersAmount) {
		if (ValidationHelper.isNegative(maxPassengersAmount)) {
			return false;
		}
		this.maxPassengersAmount = maxPassengersAmount;
		return true;
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
		int requiredFuelAmount = distance * fuelUsagePerKm;
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
		result = prime * result + fuelUsagePerKm;
		result = prime * result + ((inserviceDate == null) ? 0 : inserviceDate.hashCode());
		result = prime * result + loadCapacity;
		result = prime * result + maxPassengersAmount;
		result = prime * result + maxSpeed;
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
		if (fuelUsagePerKm != other.fuelUsagePerKm)
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
		return getClass().getName() + " [type=" + type + ", fuelUsagePerKm=" + fuelUsagePerKm + ", tankCapacity="
				+ tankCapacity + ", currentFuelAmount=" + currentFuelAmount + ", loadCapacity=" + loadCapacity
				+ ", maxSpeed=" + maxSpeed + ", crewAmount=" + crewAmount + ", maxPassengersAmount="
				+ maxPassengersAmount + ", inserviceDate=" + inserviceDate + "]";
	}

}
