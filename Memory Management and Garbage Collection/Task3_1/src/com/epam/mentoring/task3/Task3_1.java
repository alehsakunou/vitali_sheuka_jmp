/**
 * Design and implement code that will introduce:
 * java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory.
 * Do not use arrays or collections.
 * Created by Vitali_Sheuka on 7/31/2016.
 */
package com.epam.mentoring.task3;


import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;

public class Task3_1
{

    private static class BigObject
    {
        private String volume = "";

        public void addVolume(char volume)
        {
            this.volume += volume;
        }
    }

    public static void main(String[] args) throws IOException
    {
	    throwOOMError();
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory.
     * Do not use arrays or collections.
     */
    private static void throwOOMError() throws IOException
    {
        System.out.println("Loading file memtest.exe to fill memory...");
        BigObject bigObject1 = new BigObject();
        for (; ;)
        {
            FileReader fileReader = new FileReader("c:\\Boot\\memtest.exe");
            while (fileReader.ready())
            {
                bigObject1.addVolume((char) fileReader.read());
            }
            System.out.println("freeMemory: "+Runtime.getRuntime().freeMemory()/1024+" kb; total:"
                    + Runtime.getRuntime().totalMemory()/1024+" kb");
        }
    }


}
