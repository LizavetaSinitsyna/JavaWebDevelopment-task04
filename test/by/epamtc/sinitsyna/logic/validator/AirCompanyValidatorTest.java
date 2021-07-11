package by.epamtc.sinitsyna.logic.validator;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.exception.InvalidAirCompanyException;

public class AirCompanyValidatorTest {
	private AirCompanyValidator validator;
	private AirCompany company;

	@Before
	public void init() {
		validator = new AirCompanyValidator();
		company = new AirCompany();
		company.addAircraft(
				new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, 168, 25, LocalDate.parse("2020-07-08")));
		company.addAircraft(
				new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 3));
		company.addAircraft(
				new Aircraft("Airbus", 2000, 20000, 0, 15000, 650, 5, 128, 20, LocalDate.parse("2020-09-06")));
	}

	@Test
	public void testValidateWhenPassingNullCompany() throws InvalidAirCompanyException {
		Assert.assertFalse(validator.isValid(null));
	}
	
	@Test
	public void testValidateWithValidCompany() throws InvalidAirCompanyException {
		Assert.assertTrue(validator.isValid(company));
	}
	
	@Test
	public void testValidateWithInValidCompany() throws InvalidAirCompanyException {
		company.addAircraft(
				new Aircraft("Airbus", -2000, 20000, 0, 15000, 650, 5, 128, 20, LocalDate.parse("2020-09-06")));
		Assert.assertFalse(validator.isValid(company));
	}
	
	@Test
	public void testGetMessage() throws InvalidAirCompanyException {
		String expected = "Type of aircraft must be specified.";
		company.addAircraft(
				new Aircraft(null, -2000, 20000, 0, 15000, 650, 5, 128, 20, LocalDate.parse("2020-09-06")));
		validator.isValid(company);
		String actual = validator.getExceptionMessage();
		Assert.assertEquals(expected, actual);
		
	}

}
