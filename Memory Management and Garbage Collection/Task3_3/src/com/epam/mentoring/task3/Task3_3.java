package com.epam.mentoring.task3;

import java.net.URISyntaxException;

/**
 * Design and implement code that will introduce:
 * java.lang.StackOverflowError. Do not use recursive methods.
 * Created by Vitali_Sheuka on 7/31/2016.
 */

public class Task3_3 {

    public static void process(Class c) {
        System.out.println(c);
    }
    public static void main(String args[]) throws ClassNotFoundException, URISyntaxException, NoSuchFieldException, InstantiationException, IllegalAccessException {
//        System.setProperty("java.home","C:\\Program Files\\Java\\jdk1.6.0_45");
//        Class c = new JavaDynamicClassCreation().dynamicClassCreation(0);
//        process(c);

        //"C:\\Program Files\\Java\\jdk1.6.0_45"
        if (args == null || args.length < 1) {
            System.out.println("run: java -jar task3_3.jar JAVA6_HOME_PATH");
        } else {
            System.setProperty("java.home", args[0]);
            Class c = new JavaDynamicClassCreation().dynamicClassCreation(0);
            process(c);
        }

    }


}
