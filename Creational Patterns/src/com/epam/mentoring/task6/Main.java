/*
Write a class that receives all the readPerson(String name) calls
The class should delegate the request to the DB or File if no person with the matching name was already read.
Otherwise it should return a cached instance of that person.
*/
package com.epam.mentoring.task6;


import com.epam.mentoring.task6.proxy.PersonInterface;
import com.epam.mentoring.task6.proxy.PersonNotFound;
import com.epam.mentoring.task6.proxy.PersonProxy;

public class Main {

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("run: java -jar task6.jar Vitali1");
        } else {
            PersonInterface person = new PersonProxy();
            try {
                person.readPerson(args[0]);
                person.readPerson(args[0]);
                person.showPerson();
            } catch (PersonNotFound personNotFound) {
                System.err.println(personNotFound.getMessage());
            }
        }
    }
}
