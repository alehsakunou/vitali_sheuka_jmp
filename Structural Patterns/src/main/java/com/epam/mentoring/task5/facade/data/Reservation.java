package com.epam.mentoring.task5.facade.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vitali_Sheuka on 8/15/2016.
 */
@DatabaseTable(tableName = "reservations")
public class Reservation {

    public static final String NAME_FIELD_SURNAME = "surname";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String pnr;

    @DatabaseField(canBeNull = false)
    private String surname;

    @DatabaseField(canBeNull = true)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Reservation() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }

    public Reservation(String pnr, String surname, String description) {
        this.pnr = pnr;
        this.surname = surname;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", pnr='" + pnr + '\'' +
                ", surname='" + surname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
