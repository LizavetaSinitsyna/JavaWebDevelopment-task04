package by.epamtc.sinitsyna.logic.validator;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.FlyingMachine;
import by.epamtc.sinitsyna.logic.InvalidAircraftException;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AircraftValidator implements FlyingMachineValidator {

	@Override
	public void validate(FlyingMachine flyingMachine) throws InvalidAircraftException {
		if (ValidationHelper.isNull(flyingMachine)) {
			throw new InvalidAircraftException("FlyingMachine can't be null.");
		}
		if (!(flyingMachine instanceof Aircraft)) {
			throw new InvalidAircraftException("Passed FlyingMachine must belong to Aircraft.class.");
		}
		Aircraft aircraft = (Aircraft) flyingMachine;
		if (ValidationHelper.isNull(aircraft.getType())) {
			throw new InvalidAircraftException("Type of aircraft must be specified.");
		}
		if (ValidationHelper.isNegative(aircraft.getFuelUsagePerKm())) {
			throw new InvalidAircraftException("Fuel usage per km can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getTankCapacity())) {
			throw new InvalidAircraftException("Tank capacity can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getCurrentFuelAmount())) {
			throw new InvalidAircraftException("Current fuel amount can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getLoadCapacity())) {
			throw new InvalidAircraftException("Load capacity can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getMaxSpeed())) {
			throw new InvalidAircraftException("Max speed can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getCrewAmount())) {
			throw new InvalidAircraftException("Crew amount can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getMaxPassengersAmount())) {
			throw new InvalidAircraftException("Max passengers amount can't be negative.");
		}
		if (ValidationHelper.isNegative(aircraft.getMaxUnitLoadDeviceAmount())) {
			throw new InvalidAircraftException("Max unit load device amount can't be negative.");
		}

		// can be any date
		// aircraft.getInserviceDate();
	}

}
