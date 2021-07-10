package by.epamtc.sinitsyna.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import by.epamtc.sinitsyna.dao.InvalidFileDataException;
import by.epamtc.sinitsyna.dao.AirCompanyDAO;
import by.epamtc.sinitsyna.dao.DAOException;
import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;
import by.epamtc.sinitsyna.logic.FileProvider;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class FileAirСompanyDAO implements AirCompanyDAO {
	private FileProvider fileProvider = FileProvider.getInstance();
	private final static String INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE = "File doesn't consist correct company data.";

	@Override
	public AirCompany read() throws DAOException {
		File file = fileProvider.getFile();
		String line;
		String[] parameters;
		List<Field> fields;
		Field field;
		Aircraft aircraft;
		AirCompany company = new AirCompany();
		List<Aircraft> aircrafts = new ArrayList<>();

		try (Scanner scanner = new Scanner(file)) {
			line = scanner.nextLine();
			if (!line.equals(company.getClass().getSimpleName())) {
				throw new DAOException(new InvalidFileDataException(INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE));

			}
			fields = retrieveAllClassFieldsExcludingConstants(AirCompany.class);
			for (int i = 0; i < fields.size() - 1; ++i) {
				field = fields.get(i);
				parameters = scanner.nextLine().split("=");
				if (!field.getName().equals(parameters[0])) {
					throw new DAOException(new InvalidFileDataException(INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE));
				}

				setField(field, parameters[1], company);

			}

			while (scanner.hasNextLine()) {
				parameters = scanner.nextLine().split("[^a-zA-Z0-9=\\-\\.\\s]\\s?");

				aircraft = (Aircraft) Class.forName(parameters[0]).getConstructor().newInstance();
				fields = retrieveAllClassFieldsExcludingConstants(aircraft.getClass());
				if (parameters.length - 1 != fields.size()) {
					throw new DAOException(new InvalidFileDataException(INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE));
				}

				int paramsIterator = 1;
				for (int i = 0; i < fields.size(); ++i) {
					field = fields.get(i);
					line = parameters[paramsIterator];
					if (!field.getName().equals(line.split("=")[0])) {
						throw new DAOException(new InvalidFileDataException(INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE));
					}
					setField(field, line.split("=")[1], aircraft);
					++paramsIterator;
				}
				aircrafts.add(aircraft);

			}
		} catch (FileNotFoundException | IllegalArgumentException | IllegalAccessException | InstantiationException
				| ClassNotFoundException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new DAOException(e.getMessage(), e);
		}
		company.setAircrafts(aircrafts);
		return company;

	}

	private void setField(Field field, String arg, Object object) throws DAOException {
		Map<String, String> fieldTypeMatchers = new HashMap<>();
		fieldTypeMatchers.put("int", "(-|\\+)?[0-9]{1,10}");
		fieldTypeMatchers.put("String", ".*");
		fieldTypeMatchers.put("LocalDate", "(null)|\\d{4}-\\d{2}-\\d{2}");

		String typeName = field.getType().getSimpleName();

		if (ValidationHelper.isNull(fieldTypeMatchers.get(typeName)) || !arg.matches(fieldTypeMatchers.get(typeName))) {
			throw new DAOException(new InvalidFileDataException(INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE));
		}
		field.setAccessible(true);
		try {
			if (typeName.equals("int")) {
				field.set(object, Integer.parseInt(arg));
			} else if (typeName.equals("LocalDate")) {
				try {
					field.set(object, arg.equals("null") ? null : LocalDate.parse(arg));
				} catch (DateTimeParseException e) {
					throw new DAOException(new InvalidFileDataException(INVALID_FILE_STRUCTURE_EXCEPTION_MESSAGE, e));
				}
			} else {
				field.set(object, arg.equals("null") ? null : arg);
			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	private List<Field> retrieveAllClassFieldsExcludingConstants(Class<?> objectClass) {
		Field[] fields;
		List<Field> result = new ArrayList<>();

		if (objectClass == null) {
			return result;
		}

		Class<?> superObject = objectClass.getSuperclass();
		while (superObject != null) {
			fields = superObject.getDeclaredFields();
			addFieldsExcludingConstants(result, fields);
			superObject = superObject.getSuperclass();
		}

		fields = objectClass.getDeclaredFields();
		addFieldsExcludingConstants(result, fields);

		return result;

	}

	private void addFieldsExcludingConstants(List<Field> list, Field[] fields) {
		for (Field field : fields) {
			if (!(Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers()))) {
				list.add(field);
			}
		}
	}

	@Override
	public void save(AirCompany company) throws DAOException {
		if (ValidationHelper.isNull(company)) {
			throw new DAOException("Null company can't be saved.");
		}
		List<Field> fields;
		Aircraft aircraft;
		Class<?> objectClass;

		File file = fileProvider.getFile();
		try (FileWriter writer = new FileWriter(file, false)) {
			file.createNewFile();
			objectClass = company.getClass();
			writer.write(objectClass.getSimpleName() + "\n");
			fields = retrieveAllClassFieldsExcludingConstants(objectClass);
			write(writer, fields, 0, fields.size() - 1, "%s=%s\n", company);

			Iterator<Aircraft> iterator = company.getAircraftsIterator();
			while (iterator.hasNext()) {
				aircraft = iterator.next();
				objectClass = aircraft.getClass();
				writer.write(objectClass.getName() + ": ");
				fields = retrieveAllClassFieldsExcludingConstants(objectClass);
				write(writer, fields, 0, fields.size() - 1, "%s=%s, ", aircraft);
				write(writer, fields, fields.size() - 1, fields.size(), "%s=%s", aircraft);
				writer.write("\n");

			}

		} catch (IOException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	private void write(FileWriter writer, List<Field> fields, int startIndex, int endIndex, String format,
			Object object) throws DAOException {
		try {
			for (int i = startIndex; i < endIndex; ++i) {
				Field field = fields.get(i);
				field.setAccessible(true);
				writer.write(String.format(format, field.getName(), field.get(object)));
			}
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

}