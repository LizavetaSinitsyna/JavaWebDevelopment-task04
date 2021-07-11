package by.epamtc.sinitsyna.logic.validator;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.FlyingMachine;
import by.epamtc.sinitsyna.logic.exception.InvalidAircraftException;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AircraftValidator implements FlyingMachineValidator {
	private String exceptionMessage;

	@Override
	public boolean isValid(FlyingMachine flyingMachine) throws InvalidAircraftException {
		if (ValidationHelper.isNull(flyingMachine)) {
			exceptionMessage = "FlyingMachine can't be null.";
			return false;
		}
		if (!(flyingMachine instanceof Aircraft)) {
			throw new InvalidAircraftException("Passed FlyingMachine must belong to Aircraft.class.");
		}
		Aircraft aircraft = (Aircraft) flyingMachine;
		if (ValidationHelper.isNull(aircraft.getType())) {
			exceptionMessage = "Type of aircraft must be specified.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getFuelUsagePerHour())) {
			exceptionMessage = "Fuel usage per hour can't be negative.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getTankCapacity())) {
			exceptionMessage = "Tank capacity can't be negative.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getCurrentFuelAmount())) {
			exceptionMessage = "Current fuel amount can't be negative.";
			return false;
		}
		if (aircraft.getCurrentFuelAmount() > aircraft.getTankCapacity()) {
			exceptionMessage = "Current fuel amount can't be larger than tank capacity.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getLoadCapacity())) {
			exceptionMessage = "Load capacity can't be negative.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getMaxSpeed())) {
			exceptionMessage = "Max speed can't be negative.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getCrewAmount())) {
			exceptionMessage = "Crew amount can't be negative.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getMaxPassengersAmount())) {
			exceptionMessage = "Max passengers amount can't be negative.";
			return false;
		}
		if (ValidationHelper.isNegative(aircraft.getMaxUnitLoadDeviceAmount())) {
			exceptionMessage = "Max unit load device amount can't be negative.";
			return false;
		}

		return true;
	}

	@Override
	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
