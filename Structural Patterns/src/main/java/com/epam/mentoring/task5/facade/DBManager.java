package com.epam.mentoring.task5.facade;

import com.epam.mentoring.task5.facade.data.Reservation;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Vitali_Sheuka on 8/15/2016.
 */
public class DBManager {

    private static final String DATABASE_URL = "jdbc:h2:tcp://10.9.197.17:9092/reservations";

    private JdbcConnectionSource connectionSource;

    public Dao<Reservation, Integer> createDao() throws SQLException
    {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
        Dao<Reservation, Integer> dao = setupDatabase(connectionSource);
        return dao;
    }

    public void destroyDao() throws SQLException {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }

    /**
     * Setup our database and DAOs
     */
    private Dao<Reservation, Integer> setupDatabase(ConnectionSource connectionSource)
            throws SQLException
    {
        Dao<Reservation, Integer> dao = DaoManager.createDao(connectionSource, Reservation.class);
        //TableUtils.dropTable(connectionSource, Reservation.class, false);
        TableUtils.createTableIfNotExists(connectionSource, Reservation.class);
        return dao;
    }

    /**
     * Read and write some example data.
     */
    public int writeData(Dao<Reservation, Integer> dao, Reservation reservation)
            throws SQLException, DBManagerException {
        // persist the account object to the database
        dao.create(reservation);
        int id = reservation.getId();
        verifyDb(dao, id, reservation);
        return id;
    }

    /**
     * Verify that the reservation stored in the database was the same as the expected object.
     */
    private void verifyDb(Dao<Reservation, Integer> dao, int id, Reservation expected)
            throws SQLException, DBManagerException {
        // make sure we can read it back
        Reservation reservation2 = dao.queryForId(id);
        if (reservation2 == null) {
            throw new DBManagerException("Should have found id '" + id + "' in the database");
        }
    }

    public static class DBManagerException extends Exception {

        public DBManagerException(String s) {
            super(s);
        }

    }
}
