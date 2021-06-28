/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;

import by.epamtc.sinitsyna.entity.AirCompany;
import by.epamtc.sinitsyna.entity.Aircraft;

public class FileOutput {
	public void writeInFile(String path, AirCompany company) {
		Field[] superFields;
		Field[] fields;
		Aircraft aircraft;
		Class<?> objectClass;

		File file = new File(path);
		try (FileWriter writer = new FileWriter(file, true)) {
			file.createNewFile();
			objectClass = company.getClass();
			writer.write(objectClass.getSimpleName() + "\n");
			superFields = objectClass.getSuperclass().getDeclaredFields();
			write(writer, superFields, 1, superFields.length, "%s=%s\n", company);
			fields = objectClass.getDeclaredFields();
			write(writer, fields, 1, fields.length - 1, "%s=%s\n", company);

			Iterator<Aircraft> iterator = company.getAircraftsIterator();
			while (iterator.hasNext()) {
				aircraft = iterator.next();
				objectClass = aircraft.getClass();
				writer.write(objectClass.getSimpleName() + ": ");
				superFields = objectClass.getSuperclass().getDeclaredFields();
				write(writer, superFields, 1, superFields.length, "%s=%s, ", aircraft);
				fields = objectClass.getDeclaredFields();
				write(writer, fields, 1, fields.length - 1, "%s=%s, ", aircraft);
				write(writer, fields, fields.length - 1, fields.length, "%s=%s.", aircraft);
				writer.write("\n");

			}

		} catch (IOException e) {
			System.out.println(e);
			// e.printStackTrace();
		}
	}

	private void write(FileWriter writer, Field[] fields, int startIndex, int endIndex, String format, Object object) {
		try {
			for (int i = startIndex; i < endIndex; ++i) {
				Field field = fields[i];
				field.setAccessible(true);
				writer.write(String.format(format, field.getName(), field.get(object)));
			}
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			System.out.println(e);
			// e.printStackTrace();
		}
	}

}
