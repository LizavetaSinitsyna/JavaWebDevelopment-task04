package by.epamtc.sinitsyna.logic.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.logic.exception.InvalidAirCompanyException;
import by.epamtc.sinitsyna.logic.exception.InvalidFlyingMachineException;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AirCompanyValidator {
	private String exceptionMessage;
	private Map<String, FlyingMachineValidator> validators = new HashMap<>();

	{
		validators.put("Aircraft", new AircraftValidator());
		validators.put("Helicopter", new HelicopterValidator());
	}

	public boolean isValid(AirCompany company) throws InvalidAirCompanyException {
		if (ValidationHelper.isNull(company)) {
			exceptionMessage = "Company can't be null.";
			return false;
		}

		List<Aircraft> aircrafts = company.getAircrafts();
		FlyingMachineValidator validator;
		
		for (Aircraft entry : aircrafts) {
			try {
				validator = validators.get(entry.getClass().getSimpleName());
				if(!validator.isValid(entry)) {
					exceptionMessage = validator.getExceptionMessage();
					return false;
				}
				
			} catch (InvalidFlyingMachineException e) {
				throw new InvalidAirCompanyException(e.getMessage(), e);
			}
		}
		return true;
	}
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}
}
