package by.epamtc.sinitsyna.logic.validator;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.FlyingMachine;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.exception.InvalidAircraftException;
import by.epamtc.sinitsyna.logic.exception.InvalidHelicopterException;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class HelicopterValidator extends AircraftValidator {

	@Override
	public void validate(FlyingMachine flyingMachine) throws InvalidAircraftException {
		Aircraft aircraft = (Aircraft) flyingMachine;
		super.validate(aircraft);
		if (flyingMachine.getClass() != Helicopter.class) {
			throw new InvalidHelicopterException("Passed object must belong to Helicopter.class.");
		}
		Helicopter helicopter = (Helicopter) flyingMachine;
		if (ValidationHelper.isNegative(helicopter.getBladesAmount())) {
			throw new InvalidHelicopterException("Blades amoint can/t be negative.");
		}

	}
}
