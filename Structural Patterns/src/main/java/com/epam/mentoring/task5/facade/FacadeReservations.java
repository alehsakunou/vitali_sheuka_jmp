package com.epam.mentoring.task5.facade;

import com.epam.mentoring.task5.facade.data.Reservation;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vitali_Sheuka on 8/15/2016.
 */
public class FacadeReservations {

    private Dao<Reservation, Integer> mDao;
    private DBManager mDBManager;

    public void init() throws FacadeException {
        mDBManager = new DBManager();
        try {
            mDao = mDBManager.createDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FacadeException(e.getMessage());
        }
    }

    public List<Reservation> getReservations(String surname) throws FacadeException {
        if (mDao == null) throw new FacadeException("FacadeReservations not initialised!");
        if (surname == null) throw new IllegalArgumentException();
        List<Reservation> results = null;
        try {
            QueryBuilder<Reservation, Integer> statementBuilder = mDao.queryBuilder();
            SelectArg selectArg = new SelectArg();
            // build a query with the WHERE clause set to 'name = ?'
            statementBuilder.where().like(Reservation.NAME_FIELD_SURNAME, selectArg);
            PreparedQuery<Reservation> preparedQuery = statementBuilder.prepare();

            // now we can set the select arg (?) and run the query
            selectArg.setValue(surname);
            results = mDao.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FacadeException(e.getMessage());
        }
        return results;
    }

    public void addReservation(String pnr, String surname, String description) throws FacadeException {
        if (mDao == null) throw new FacadeException("FacadeReservations not initialised!");
        if (pnr == null || surname == null) throw new IllegalArgumentException();
        try {
            mDBManager.writeData(mDao, new Reservation(pnr, surname, description));
        } catch (SQLException | DBManager.DBManagerException e) {
            e.printStackTrace();
            throw new FacadeException(e.getMessage());
        }
    }

    public void destroy() throws FacadeException {
        if (mDBManager == null) throw new FacadeException("FacadeReservations not initialised!");
        try {
            mDBManager.destroyDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FacadeException(e.getMessage());
        }
    }

    public static class FacadeException extends Exception {

        public FacadeException(String message) {
            super(message);
        }

    }
}
