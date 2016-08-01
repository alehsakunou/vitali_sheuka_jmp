package com.epam.mentoring.task6.proxy;

import java.io.*;

/**
 * Created by Vitali_Sheuka on 8/1/2016.
 */
public class Person implements PersonInterface {

    private static final String PERSONFILENAME = "person.dat";

    private String uid;
    private String name;
    private String surname;
    private String age;
    private String sex;

    protected Person() {
    }

    protected Person(String[] pInfo) {
        this(pInfo[0],pInfo[1],pInfo[2],pInfo[3],pInfo[4]);
    }

    public Person(String uid, String name, String surname, String age, String sex) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public Person readPerson(String name) throws PersonNotFound {
        Person res = loadFromFile(name);
        return res;
    }

    @Override
    public void showPerson() {
        System.out.println(toString());
    }

    protected synchronized static Person loadFromFile(String name) throws PersonNotFound {
        Person res = null;
        BufferedReader br = null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(PERSONFILENAME);
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                String[] pInfo = line.split(";");
                if (pInfo[1].equals(name)) {
                    res = new Person(pInfo);
                    break;
                }
            }
            if (res == null) throw new PersonNotFound();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
