package by.epamtc.sinitsyna.logic;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.entity.Helicopter;
import by.epamtc.sinitsyna.logic.comparator.FuelUsageAscendingComparator;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AirCompanyLogicTest {
	private AirCompanyLogic logic;
	private AirCompany company;

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void initLogicAndCompany() {
		logic = ServiceProvider.getInstance().getLogic();
		company = new AirCompany();
		company.addAircraft(
				new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, 168, 25, LocalDate.parse("2020-07-08")));
		company.addAircraft(
				new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 3));
		company.addAircraft(
				new Aircraft("Airbus", 2000, 20000, 0, 15000, 650, 5, 128, 20, LocalDate.parse("2020-09-06")));
	}

	@Test
	public void testReadWhenPassingFileWithValidData() throws ServiceException {
		AirCompany expected = new AirCompany();
		expected.setRegistrationNumber("19834567");
		expected.setCountryOfRegistration("Belarus");
		expected.setName("MinskTest");
		expected.setRegistrationType("LLC");
		expected.setLegalAddress("RB, Minsk, Klary Tsetkin Street, 51A");
		expected.setPostAddress(null);
		expected.addAircraft(new Helicopter("Bell", 32, 840, 0, 1760, 238, 1, 14, 0, LocalDate.parse("2019-01-31"), 2));
		expected.addAircraft(
				new Aircraft("Boeing", 2600, 26020, 13010, 20540, 880, 2, 189, 200, LocalDate.parse("2018-08-27")));
		FileProvider.getInstance().setFile(new File(".\\test\\resource\\ValidAirCompanyForTestRead.txt"));
		AirCompany actual = logic.read();
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = ServiceException.class)
	public void testReadWhenPassingFileWithInvalidDataForDAO() throws ServiceException {
		FileProvider.getInstance().setFile(new File(".\\test\\resource\\InvalidAirCompanyForTestRead.txt"));
		logic.read();
	}

	@Test(expected = ServiceException.class)
	public void testReadWhenPassingFileWithInvalidParameterValue() throws ServiceException {
		FileProvider.getInstance().setFile(new File(".\\test\\resource\\AirCompanyWithInvalidParameterValueForTestLogicRead.txt"));
		logic.read();
	}

	@Test
	public void testSave() throws IOException, ServiceException {
		List<String> expected = new ArrayList<>();
		expected.add("AirCompany\n");
		expected.add("registrationNumber=null\n");
		expected.add("countryOfRegistration=null\n");
		expected.add("name=null\n");
		expected.add("registrationType=null\n");
		expected.add("legalAddress=null\n");
		expected.add("postAddress=null\n");
		expected.add(
				"by.epamtc.sinitsyna.entity.Aircraft: type=Boeing, fuelUsagePerHour=2000, tankCapacity=25000, currentFuelAmount=1500, loadCapacity=20000, maxSpeed=750, crewAmount=10, maxPassengersAmount=168, maxUnitLoadDeviceAmount=25, inserviceDate=2020-07-08\n");
		expected.add(
				"by.epamtc.sinitsyna.entity.Helicopter: type=Bell, fuelUsagePerHour=100, tankCapacity=800, currentFuelAmount=500, loadCapacity=1500, maxSpeed=250, crewAmount=1, maxPassengersAmount=2, maxUnitLoadDeviceAmount=10, inserviceDate=2021-01-31, bladesAmount=3\n");
		expected.add(
				"by.epamtc.sinitsyna.entity.Aircraft: type=Airbus, fuelUsagePerHour=2000, tankCapacity=20000, currentFuelAmount=0, loadCapacity=15000, maxSpeed=650, crewAmount=5, maxPassengersAmount=128, maxUnitLoadDeviceAmount=20, inserviceDate=2020-09-06\n");

		File file = temporaryFolder.newFile("test.txt");
		FileProvider.getInstance().setFile(file);
		logic.save(company);

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		int linesCounter = 0;
		while (!ValidationHelper.isNull(line) || linesCounter < expected.size()) {
			if (!ValidationHelper.isNull(line) && line.equals(expected.get(linesCounter))) {
				String failure = String.format("expected %s, but was %s", expected.get(linesCounter), line);
				Assert.fail(String.format("File contains incorrect data on line №%d: %s", linesCounter, failure));
			}
			line = bufferedReader.readLine();
			linesCounter++;
		}
		if (ValidationHelper.isNull(line) && linesCounter != expected.size()
				|| !ValidationHelper.isNull(line) && linesCounter == expected.size()) {
			Assert.fail("File contains incorrect data on line №" + linesCounter);
		}
		bufferedReader.close();
	}

	@Test(expected = ServiceException.class)
	public void testSaveWhenPassingNullCompany() throws ServiceException {
		logic.save(null);
	}

	@Test
	public void testRetrieveGeneralLoadCapacity() throws ServiceException {
		BigInteger expected = new BigInteger("36500");
		BigInteger actual = logic.retrieveGeneralLoadCapacity(company);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = ServiceException.class)
	public void testRetrieveGeneralLoadCapacityWhenPassingNullCompany() throws ServiceException {
		logic.retrieveGeneralLoadCapacity(null);
	}

	@Test
	public void testRetrieveGeneralPassengersCapacity() throws ServiceException {
		BigInteger expected = new BigInteger("298");
		BigInteger actual = logic.retrieveGeneralPassengersCapacity(company);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = ServiceException.class)
	public void testRetrieveGeneralPassengersCapacityWhenPassingNullCompany() throws ServiceException {
		logic.retrieveGeneralPassengersCapacity(null);
	}

	@Test
	public void testSortByFuelUsagePerHourInAscendingOrder() {
		List<Aircraft> expected = new ArrayList<>();
		expected.add(new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 3));
		expected.add(new Aircraft("Boeing", 2000, 25000, 1500, 20000, 750, 10, 168, 25, LocalDate.parse("2020-07-08")));
		expected.add(new Aircraft("Airbus", 2000, 20000, 0, 15000, 650, 5, 128, 20, LocalDate.parse("2020-09-06")));
		logic.sort(company, new FuelUsageAscendingComparator());
		Assert.assertEquals(expected, company.getAircrafts());
	}

	@Test
	public void testSortByPassingNullCompany() {
		AirCompany actual = null;
		logic.sort(actual, new FuelUsageAscendingComparator());
		Assert.assertEquals(null, actual);
	}

	@Test
	public void testSortByPassingNullComparator() {
		List<Aircraft> expected = company.getAircrafts();
		logic.sort(company, null);
		Assert.assertEquals(expected, company.getAircrafts());

	}

	@Test
	public void testRetrieveAircraftsByFuelUsage() throws ServiceException {
		List<Aircraft> expected = new ArrayList<>();
		expected.add(new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 3));
		List<Aircraft> actual = logic.retrieveAircraftsByFuelUsage(company, 0, 100);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = ServiceException.class)
	public void testRetrieveAircraftsByFuelUsageByPassingNullCompany() throws ServiceException {
		logic.retrieveAircraftsByFuelUsage(null, 0, 100);
	}

	@Test
	public void testRetrieveAircraftsByFuelUsageWhenFirstBorderIsLargerThanSecond() throws ServiceException {
		List<Aircraft> expected = new ArrayList<>();
		expected.add(new Helicopter("Bell", 100, 800, 500, 1500, 250, 1, 2, 10, LocalDate.parse("2021-01-31"), 3));
		List<Aircraft> actual = logic.retrieveAircraftsByFuelUsage(company, 100, 0);
		Assert.assertEquals(expected, actual);
	}

}
