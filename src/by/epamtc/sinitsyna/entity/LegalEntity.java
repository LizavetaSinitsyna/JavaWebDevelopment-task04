/*Авиакомпания. Определить иерархию самолетов. Создать авиакомпанию. Посчитать общую вместимость и грузоподъемность. 
 Провести сортировку самолетов компании на основе одного и нескольких параметров. 
 Найти самолет в компании, соответствующий заданному диапазону параметров потребления горючего.
 */

package by.epamtc.sinitsyna.entity;

import java.io.Serializable;

public class LegalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String registrationNumber;
	private String countryOfRegistration;
	private String name;
	private String registrationType;
	private String legalAddress;
	private String postAddress;

	public LegalEntity() {
	}

	public LegalEntity(String name) {
		this.name = name;
	}

	public LegalEntity(String registrationNumber, String countryOfRegistration, String name, String registrationType,
			String legalAddress, String postAddress) {
		this.registrationNumber = registrationNumber;
		this.countryOfRegistration = countryOfRegistration;
		this.name = name;
		this.registrationType = registrationType;
		this.legalAddress = legalAddress;
		this.postAddress = postAddress;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getCountryOfRegistration() {
		return countryOfRegistration;
	}

	public void setCountryOfRegistration(String countryOfRegistration) {
		this.countryOfRegistration = countryOfRegistration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	public String getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(String legalAddress) {
		this.legalAddress = legalAddress;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryOfRegistration == null) ? 0 : countryOfRegistration.hashCode());
		result = prime * result + ((legalAddress == null) ? 0 : legalAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postAddress == null) ? 0 : postAddress.hashCode());
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		result = prime * result + ((registrationType == null) ? 0 : registrationType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LegalEntity other = (LegalEntity) obj;
		if (countryOfRegistration == null) {
			if (other.countryOfRegistration != null)
				return false;
		} else if (!countryOfRegistration.equals(other.countryOfRegistration))
			return false;
		if (legalAddress == null) {
			if (other.legalAddress != null)
				return false;
		} else if (!legalAddress.equals(other.legalAddress))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postAddress == null) {
			if (other.postAddress != null)
				return false;
		} else if (!postAddress.equals(other.postAddress))
			return false;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		if (registrationType == null) {
			if (other.registrationType != null)
				return false;
		} else if (!registrationType.equals(other.registrationType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [registrationNumber=" + registrationNumber + ", countryOfRegistration="
				+ countryOfRegistration + ", name=" + name + ", registrationType=" + registrationType
				+ ", legalAddress=" + legalAddress + ", postAddress=" + postAddress + "]";
	}

}
