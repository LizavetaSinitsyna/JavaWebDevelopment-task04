package by.epamtc.sinitsyna.logic.validator;

import org.junit.Assert;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.exception.InvalidAircraftException;
import by.epamtc.sinitsyna.logic.exception.InvalidHelicopterException;

public class HelicopterValidatorTest {
	private HelicopterValidator validator;

	@Before
	public void init() {
		validator = new HelicopterValidator();
	}

	@Test
	public void testIsValidWhenPassingValidHelicopter() throws InvalidAircraftException {
		Helicopter helicopter = new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 5);
		Assert.assertTrue(validator.isValid(helicopter));
	}
	
	@Test
	public void testIsValidWhenPassingNullHelicopter() throws InvalidAircraftException {
		Helicopter helicopter = null;
		Assert.assertFalse(validator.isValid(helicopter));
	}
	
	@Test
	public void testIsValidWhenPassingInvalidHelicopter() throws InvalidAircraftException {
		Helicopter helicopter = new Helicopter(null, 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 5);
		Assert.assertFalse(validator.isValid(helicopter));
	}
	
	@Test(expected = InvalidHelicopterException.class)
	public void testIsValidWhenPassingNonHelicopter() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		validator.isValid(aircraft);
	}

	@Test
	public void testGetExceptionMessage() throws InvalidAircraftException {
		Helicopter helicopter = new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), -5);
		String expected = "Blades amount can't be negative.";
		validator.isValid(helicopter);
		String actual = validator.getExceptionMessage();
		Assert.assertEquals(expected, actual);
	}

}
