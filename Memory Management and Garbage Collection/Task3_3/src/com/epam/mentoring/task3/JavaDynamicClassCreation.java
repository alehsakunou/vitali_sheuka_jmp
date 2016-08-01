package com.epam.mentoring.task3;

import sun.misc.Unsafe;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;

import static java.util.Collections.singletonList;
import static javax.tools.JavaFileObject.Kind.SOURCE;

public class JavaDynamicClassCreation {

    public Class dynamicClassCreation(int i) throws ClassNotFoundException, IllegalAccessException, InstantiationException, URISyntaxException, NoSuchFieldException {

        final String className = "DynamicClass" + i;
        final String path = "com.epam.mentoring.task3";
        final String fullClassName = path.replace('.', '/') + "/" + className;

        final StringBuilder source = new StringBuilder();
        source.append("package " + path + ";");
        source.append("public class " + className + " {\n");
        source.append("public String string=\"string").append(i).append("\"").append(";\n");
        source.append(" public String toStringNew(){\n");
        final int COUNT = 2025;
        for (int j = 0; j < COUNT; j++) {
            source.append("double var").append(j).append("=").append(j).append(";\n");
        }

        source.append("double sum").append("=");
        for (int k = 0; k < COUNT - 2; k++) {
            source.append("var").append(k).append("+");
        }
        source.append("var").append(COUNT - 1).append(";\n");


        source.append("     return \"DynamicClass - Java Dynamic Class Creation\"")/*.append("+sum")*/.append(";");
        source.append(" }\n");

        source.append(" public String toString() {\n");
        source.append("     return \"DynamicClass - Java Dynamic Class Creation\";");
        source.append(" }\n");

        source.append("}\n");

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        final SimpleJavaFileObject simpleJavaFileObject
                = new SimpleJavaFileObject(URI.create(fullClassName + ".java"), SOURCE) {

            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }

            @Override
            public OutputStream openOutputStream() throws IOException {
                return byteArrayOutputStream;
            }
        };

        final JavaFileManager javaFileManager = new ForwardingJavaFileManager(
                ToolProvider.getSystemJavaCompiler().getStandardFileManager(null, null, null)) {

            @Override
            public JavaFileObject getJavaFileForOutput(Location location,
                                                       String className,
                                                       JavaFileObject.Kind kind,
                                                       FileObject sibling) throws IOException {
                return simpleJavaFileObject;
            }
        };

        ToolProvider.getSystemJavaCompiler().getTask(
                null, javaFileManager, null, null, null, singletonList(simpleJavaFileObject)).call();

        final byte[] bytes = byteArrayOutputStream.toByteArray();

        final Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        final Unsafe unsafe = (Unsafe) f.get(null);
        try {
            final Class aClass = unsafe.defineClass(fullClassName, bytes, 0, bytes.length);
            return aClass;
        }catch (Throwable e) {
            //e.printStackTrace();
        }
        return null;

    }

}