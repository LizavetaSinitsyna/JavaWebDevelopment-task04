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
	private Map<String, FlyingMachineValidator> validators = new HashMap<>();

	{
		validators.put("Aircraft", new AircraftValidator());
		validators.put("Helicopter", new HelicopterValidator());
	}

	public void validate(AirCompany company) throws InvalidAirCompanyException {
		if (ValidationHelper.isNull(company)) {
			throw new InvalidAirCompanyException("Company can't be null.");
		}
		List<Aircraft> aircrafts = company.getAircrafts();
		for (Aircraft entry : aircrafts) {
			try {
				validators.get(entry.getClass().getSimpleName()).validate(entry);
			} catch (InvalidFlyingMachineException e) {
				throw new InvalidAirCompanyException(e.getMessage(), e);
			}
		}
	}
}
