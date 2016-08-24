package com.epam.mentoring.multithreading.classic;

import com.epam.mentoring.multithreading.Loader;

import java.io.*;
import java.util.List;
import java.util.concurrent.Future;


/**
 * Created by Vitali_Sheuka on 8/22/2016.
 */
public class ClassicLoader extends Loader {

    public ClassicLoader(LoadListener listener) {
        super(listener);
    }

    @Override
    public void uploadFile(final File from, final String to) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                upload(from, to);
            }
        });
        thread.start();
    }

    @Override
    public void downloadFile(String from, File to) {
        //http://127.0.0.1:8888/uploads/1.jpg
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                download(from, to);
            }
        });
        thread.start();
    }

    @Override
    public List<Future<String>> execute() throws LoaderException {
        throw new LoaderException(this+":execute not supported!");
    }

    @Override
    public void shutdown() throws LoaderException {
        throw new LoaderException(this+":shutdown not supported!");
    }
}
