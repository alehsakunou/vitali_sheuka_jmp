package com.epam.mentoring.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Design and implement code that will introduce:
 * java.lang.OutOfMemoryError: Metaspace. Load classes continuously and make them stay in memory.
 * Created by Vitali_Sheuka on 7/31/2016.
 */
public class Task3_2
{
    private static List classes = new ArrayList();

    public static void main(String[] args) throws Exception {

        for (int i = 0; ; i++) {
            Class c = ClassLoader.getSystemClassLoader().loadClass("javax.swing.JTable");
            classes.add(c.newInstance());
        }
    }
}
