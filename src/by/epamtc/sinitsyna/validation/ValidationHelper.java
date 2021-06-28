/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.validation;

public class ValidationHelper {

	public static boolean isNegative(int number) {
		return number < 0;
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

}
