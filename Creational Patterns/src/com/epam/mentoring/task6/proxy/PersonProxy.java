package com.epam.mentoring.task6.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vitali_Sheuka on 8/1/2016.
 */
public class PersonProxy implements PersonInterface {

    private static Map<String, Person> cache = new HashMap<>();
    private Person person = null;

    @Override
    public Person readPerson(String name) throws PersonNotFound {
        Person res = cache.get(name);
        if (res == null) {
            System.out.println(name + " not found in cache! Loading from file...");
            res = new Person().readPerson(name);
            cache.put(name,res);
        } else {
            System.out.println(name + " has found in cache!");
        }
        person = res;
        return res;
    }

    @Override
    public void showPerson() throws PersonNotFound {
        if (person != null) {
            person.showPerson();
        } else {
            throw new PersonNotFound();
        }
    }
}
