package by.epamtc.sinitsyna.logic.validator;

import by.epamtc.sinitsyna.entity.FlyingMachine;
import by.epamtc.sinitsyna.logic.exception.InvalidFlyingMachineException;

public interface FlyingMachineValidator {
	boolean isValid(FlyingMachine flyingMachine) throws InvalidFlyingMachineException;
	String getExceptionMessage();
}
