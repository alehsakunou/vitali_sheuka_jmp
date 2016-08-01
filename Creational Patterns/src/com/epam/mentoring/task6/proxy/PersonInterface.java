package com.epam.mentoring.task6.proxy;

/**
 * Created by Vitali_Sheuka on 8/1/2016.
 */
public interface PersonInterface {
    Person readPerson(String name) throws PersonNotFound;
    void showPerson() throws PersonNotFound;
}
