package com.epam.mentoring.multithreading.concurency;

import com.epam.mentoring.multithreading.Loader;
import javafx.concurrent.Task;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Vitali_Sheuka on 8/22/2016.
 */
public class ConcurrencyLoader extends Loader {

    private final ExecutorService mExecutorService;
    private final Set<Callable<String>> mCallables;

    public ConcurrencyLoader() {
        super();
        mExecutorService = Executors.newSingleThreadExecutor();
        mCallables = new HashSet<Callable<String>>();
    }

    @Override
    public void uploadFile(File from, String to) {
        mCallables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return upload(from, to);
            }
        });
    }

    @Override
    public void downloadFile(String from, File to) {
        mCallables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return download(from, to);
            }
        });
    }

    @Override
    public List<Future<String>> execute() throws LoaderException {
        try {
            return mExecutorService.invokeAll(mCallables);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            throw new LoaderException(e.getMessage());
        }
    }

    @Override
    public void shutdown() throws LoaderException {
        try {
            mExecutorService.shutdown();
        } catch (SecurityException e) {
            throw new LoaderException(e.getMessage());
        }
    }
}
