/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import by.epamtc.sinitsyna.exception.IndexOutOfBoundsException;
import by.epamtc.sinitsyna.validation.ValidationHelper;

public class AirCompany extends LegalEntity {
	private static final long serialVersionUID = 1L;

	private List<Aircraft> aircrafts;

	public AirCompany() {
		this.aircrafts = new ArrayList<Aircraft>();
	}

	public AirCompany(List<Aircraft> aircrafts) {
		if (ValidationHelper.isNull(aircrafts)) {
			this.aircrafts = new ArrayList<Aircraft>();
		}
		this.aircrafts = retrieveListCopy(aircrafts);
	}

	public List<Aircraft> getAircrafts() {
		return Collections.unmodifiableList(aircrafts);
	}

	public Iterator<Aircraft> getAircraftsIterator() {
		return aircrafts.iterator();
	}

	public boolean setAircrafts(List<Aircraft> aircrafts) {
		if (ValidationHelper.isNull(aircrafts)) {
			return false;
		}
		this.aircrafts = retrieveListCopy(aircrafts);
		return true;
	}

	public Aircraft get(int index) throws IndexOutOfBoundsException {
		if (!isIndexValid(index)) {
			throw new IndexOutOfBoundsException("Index can't be less than 0 or larger than list's length.");
		}
		return aircrafts.get(index);
	}

	public boolean set(int index, Aircraft aircraft) {
		if (!isIndexValid(index) || ValidationHelper.isNull(aircraft)) {
			return false;
		}
		aircrafts.set(index, null);
		return true;
	}

	public boolean addAircraft(Aircraft aircraft) {
		if (ValidationHelper.isNull(aircraft)) {
			return false;
		}
		aircrafts.add(aircraft);
		return true;

	}

	public void removeAircraft(Aircraft aircraft) {
		aircrafts.remove(aircraft);
	}

	public int retrieveAircraftsAmount() {
		return aircrafts.size();
	}

	private List<Aircraft> retrieveListCopy(List<Aircraft> aircrafts) {
		List<Aircraft> copy = new ArrayList<>();
		for (Aircraft entry : aircrafts) {
			if (entry != null) {
				copy.add(entry);
			}
		}
		return copy;
	}

	private boolean isIndexValid(int index) {
		return !ValidationHelper.isNegative(index) && index < aircrafts.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aircrafts == null) ? 0 : aircrafts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirCompany other = (AirCompany) obj;
		if (aircrafts == null) {
			if (other.aircrafts != null)
				return false;
		} else if (!aircrafts.equals(other.aircrafts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [aircrafts=" + aircrafts + "]";
	}

}
