package by.epamtc.sinitsyna.logic.validator;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.FlyingMachine;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.exception.InvalidAircraftException;
import by.epamtc.sinitsyna.logic.exception.InvalidHelicopterException;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class HelicopterValidator extends AircraftValidator {
	private String exceptionMessage;

	@Override
	public boolean isValid(FlyingMachine flyingMachine) throws InvalidAircraftException {
		Aircraft aircraft = (Aircraft) flyingMachine;
		if(!super.isValid(aircraft)) {
			exceptionMessage = super.getExceptionMessage();
			return false;
		}
		if (flyingMachine.getClass() != Helicopter.class) {
			throw new InvalidHelicopterException("Passed object must belong to Helicopter.class.");
		}
		Helicopter helicopter = (Helicopter) flyingMachine;
		if (ValidationHelper.isNegative(helicopter.getBladesAmount())) {
			exceptionMessage = "Blades amount can't be negative.";
			return false;
		}
		return true;

	}
	
	@Override
	public String getExceptionMessage() {
		return exceptionMessage;
	}
}
