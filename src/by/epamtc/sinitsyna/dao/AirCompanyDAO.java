package by.epamtc.sinitsyna.dao;

import by.epamtc.sinitsyna.entity.AirCompany;

public interface AirCompanyDAO {
	AirCompany read() throws DAOException;

	void save(AirCompany company) throws DAOException;
}
