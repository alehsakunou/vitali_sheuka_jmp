package com.epam.mentoring.task2;

/**
 * Created by Vitali_Sheuka on 8/10/2016.
 */

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class JarClassLoader extends ClassLoader {

    private static final Logger logger = LogManager.getLogger(JarClassLoader.class);

    private HashMap<String, Class<?>> cache = new HashMap<String, Class<?>>();
    private String jarFileName;
    private String packageName;

    public JarClassLoader(String fileName, String packageName) {
        this.jarFileName = fileName;
        this.packageName = packageName;
        cacheClasses();
    }

    private void cacheClasses() {
        try {
            JarFile jarFile = new JarFile(jarFileName);
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) entries.nextElement();
                if (match(normalize(jarEntry.getName()), packageName)) {
                    byte[] classData = loadClassBinary(jarFile, jarEntry);
                    if (classData != null) {
                        Class<?> clazz =
                                defineClass(stripClassName(normalize(jarEntry.getName())), classData, 0, classData.length);
                        cache.put(clazz.getName(), clazz);
                        logger.log(Level.DEBUG,"Class " + clazz.getName() + " cached");
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result = cache.get(name);
        if (result == null) {
            result = cache.get(packageName + "." + name);
        }
        if (result == null) {
            result = super.findSystemClass(name);
        }
        logger.log(Level.DEBUG,"loadClass(" + name + ")");
        return result;
    }

    private String stripClassName(String className) {
        return className.substring(0, className.length() - 6);
    }


    private String normalize(String className) {
        return className.replace('/', '.');
    }

    private boolean match(String className, String packageName) {
        return className.startsWith(packageName) && className.endsWith(".class");
    }


    private byte[] loadClassBinary(JarFile jarFile, JarEntry jarEntry) throws IOException {
        long size = jarEntry.getSize();
        if (size == -1 || size == 0) {
            return null;
        }
        byte[] data = new byte[(int) size];
        InputStream in = jarFile.getInputStream(jarEntry);
        in.read(data);
        return data;
    }

}
