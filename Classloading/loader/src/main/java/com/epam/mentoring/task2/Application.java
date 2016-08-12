package com.epam.mentoring.task2;

import com.epam.mentoring.task2.lib.Existing;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import java.util.Scanner;

/**
 * Create console application for dynamic reloading of existing development functionality.
 * Existing funcionality should be placed in specifed directory and should be archived in jar.
 * The application should have console menu for choosing option, the output should be done through usage of log4j library.
 * Created by Vitali_Sheuka on 8/10/2016.
 */
public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException
    {
        URL location = JarClassLoader.class.getProtectionDomain().getCodeSource().getLocation();
        String jar1 = new File(location.getFile()).getParent()+"/impl1-1.0-SNAPSHOT.jar";
        String jar2 = new File(location.getFile()).getParent()+"/impl2-1.0-SNAPSHOT.jar";

        logger.log(Level.DEBUG,"Jar 1: "+jar1);
        logger.log(Level.DEBUG,"Jar 2: "+jar2);

        LoaderInterface obj = new Existing();
        logger.log(Level.DEBUG,"run default method doSomething(): "+obj.doSomething());

        int i = 0;
        do {
            i = showMenu();
            String jar = jar1;
            switch (i) {
                case 1:{jar = jar1; break;}
                case 2:{jar = jar2;}
            }
            JarClassLoader jarClassLoader =
                    new JarClassLoader(jar,
                            "com.epam.mentoring.task2.lib");
            Class<?> ext = jarClassLoader.loadClass("Existing");
            LoaderInterface loaded = (LoaderInterface) ext.newInstance();
            logger.log(Level.DEBUG,"run loaded form "+jar+" method doSomething(): "+loaded.doSomething());
        } while (i!=3);







//
//        //===
//        jar = new File(location.getFile()).getParent()+"/impl1-1.0-SNAPSHOT.jar";
//        System.out.println("jar = "+jar);
//        jarClassLoader =
//                new JarClassLoader(jar,
//                        "com.epam.mentoring.task2.lib");
//
//        ext1 = jarClassLoader.loadClass("Existing");
//
//        sample = (LoaderInterface) ext1.newInstance();
//        sample.doSomething();

    }

    private static int showMenu() {
        System.out.println("Select operation:");
        System.out.println("1 : load Jar 1 and run doSomething()");
        System.out.println("2 : load Jar 2 and run doSomething()");
        System.out.println("3 : quit");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        return i;
    }



}
