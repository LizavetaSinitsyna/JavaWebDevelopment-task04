package by.epamtc.sinitsyna.logic.validator;

import org.junit.Assert;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.logic.exception.InvalidAircraftException;

public class AircraftValidatorTest {
	private AircraftValidator validator;

	@Before
	public void init() {
		validator = new AircraftValidator();
	}

	@Test
	public void testIsValidWhenPassingValidAircraft() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertTrue(validator.isValid(aircraft));
	}
	
	@Test
	public void testIsValidWhenPassingNullAircraft() throws InvalidAircraftException {
		Aircraft aircraft = null;
		Assert.assertFalse(validator.isValid(aircraft));
	}
	
	@Test
	public void testIsValidWhenPassingNullType() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft(null, 2000, 25000, 1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));
	}

	@Test
	public void testIsValidWhenPassingNegativeFuelUsagePerHour() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", -2000, 25000, 1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativeTankCapacity() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, -25000, 1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativeCurrentFuelAmount() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, -1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}
	
	@Test
	public void testIsValidWhenCurrentFuelAmountIsLargerThanTankCapacity() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 30000, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativeLoadCapacity() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, -20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativeMaxSpeed() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, 20000, -750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativeCrewAmount() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, -10, 168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativeMaxPassengersAmount() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, -168, 25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testIsValidWhenPassingNegativegetMaxUnitLoadDeviceAmount() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, 168, -25,
				LocalDate.parse("2020-07-08"));
		Assert.assertFalse(validator.isValid(aircraft));

	}

	@Test
	public void testGetExceptionMessage() throws InvalidAircraftException {
		Aircraft aircraft = new Aircraft("Boeing", 2000, -25000, 1500, 20000, 750, 10, 168, 25,
				LocalDate.parse("2020-07-08"));
		String expected = "Tank capacity can't be negative.";
		validator.isValid(aircraft);
		String actual = validator.getExceptionMessage();
		Assert.assertEquals(expected, actual);
	}

}
