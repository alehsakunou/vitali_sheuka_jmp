package com.epam.mentoring.task2.lib;


import com.epam.mentoring.task2.LoaderInterface;

/**
 * Created by Vitali_Sheuka on 8/10/2016.
 */
public class Existing implements LoaderInterface {


    public Existing() {
    }

    @Override
    public String toString() {
        return "Existing1{}";
    }

    @Override
    public String doSomething() {
        return "doSomething->"+toString();
    }
}
