package com.epam.mentoring.task5;

import com.epam.mentoring.task5.facade.FacadeReservations;

/**
 * On the base of Facade implement class, organize the connection to database.
 */
public class Application {

    public static void main(String[] args) throws FacadeReservations.FacadeException {
        FacadeReservations frm = new FacadeReservations();
        frm.init();
        frm.addReservation("5N2PN5","Sheuka","not ticketed");
        frm.addReservation("5NYRAW","Sheuka","not ticketed");
        frm.addReservation("5NYRAW","Sheuka2","not ticketed");
        frm.addReservation("5OM6Y7","Sheuka","with child and infant");
        frm.addReservation("5Q2T9Y","Sheuka","offline");
        frm.addReservation("5Q2T9Y","Sheuka2","offline");
        System.out.println(frm.getReservations("Sheuka2"));
        frm.destroy();
    }

}
