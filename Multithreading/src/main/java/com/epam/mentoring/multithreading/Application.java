package com.epam.mentoring.multithreading;


import com.epam.mentoring.multithreading.classic.ClassicLoader;
import com.epam.mentoring.multithreading.concurency.ConcurrencyLoader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This is continuation of the task "Principle of software development. SOLID File share service".

 Implement the following use cases:

 Upload file.

 Download file.


 It should be possible to upload two or more files in parallel.
 It should be possible to download two or more files in parallel.
 Console commands or script should be provided to demonstrate the upload and download. No UI required.


 Code to interface: implement classes for uploading and downloading using classic multithreading model
 and then the same functionality using concurrency. Provide pros and cons of each approach.
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("upload&dowload");
        //"d:\\git\\vitali_sheuka_jmp\\Multithreading\\src\\test\\resources\\"
        if (args.length < 4) {
            System.out.println("wrong arguments, example: java -jar /path/upload/from http://127.0.0.1:8888/uploads.php http://127.0.0.1:8888/uploads/ /path/to/download");
        } else {
            String dirName = args[0];
            try {
                //"http://127.0.0.1:8888/uploads.php"
                runClassicUpload(dirName, args[1]);
                runConcurrencyUpload(dirName, args[1]);
            } catch (Loader.LoaderException e) {
                e.printStackTrace();
            }
            try {
                //"http://127.0.0.1:8888/uploads/", "d:\\bin\\xampp\\htdocs\\downloads\\"
                runClassicDownload(args[2],args[3]);
                runConcurrencyDownload(args[2],args[3]);
            } catch (Loader.LoaderException e) {
                e.printStackTrace();
            }
        }

    }

    private static void runClassicDownload(String url, String dirName) throws Loader.LoaderException {
        System.out.println("--- runClassicDownload start ---");
        Loader loader = new ClassicLoader(new Loader.LoadListener() {
            @Override
            public void onLoad(String msg) {
                System.out.println(msg);
            }

            @Override
            public void onError(Loader.LoaderException e) {
                System.err.println(e.getMessage());
            }
        });
        for (int i=1;i<8;i++) {
            downloadFile(loader, url, dirName, i + ".jpg");
        }
        System.out.println("--- runClassicDownload end---");
    }

    private static void runClassicUpload(String dirName, String url) throws Loader.LoaderException {
        System.out.println("--- runClassicUpload start ---");
        Loader loader = new ClassicLoader(new Loader.LoadListener() {
            @Override
            public void onLoad(String msg) {
                System.out.println(msg);
            }

            @Override
            public void onError(Loader.LoaderException e) {
                System.err.println(e.getMessage());
            }
        });
        uploadDirectory(loader,dirName,url);
        System.out.println("--- runClassicUpload end---");
    }

    private static void uploadDirectory(Loader loader, String dirName, String url) throws Loader.LoaderException {
        File folder = new File(dirName);
        if (!folder.isDirectory()) {
            throw new Loader.LoaderException("Wrong directory name! " + folder);
        } else {
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    loader.uploadFile(file,url);
                }
            }
        }
    }

    private static void downloadFile(Loader loader, String url, String dirName, String fileName) throws Loader.LoaderException {
        File file = new File(dirName+fileName);
        if (file.isDirectory()) {
            throw new Loader.LoaderException("Wrong file name! " + file);
        } else {
            loader.downloadFile(url + fileName, file);
        }
    }

    private static void runConcurrencyDownload(String url, String dirName) throws Loader.LoaderException {
        System.out.println("--- runConcurrencyDownload start ---");
        Loader loader = new ConcurrencyLoader();
        for (int i=1;i<8;i++) {
            downloadFile(loader, url, dirName, i + ".jpg");
        }
        try {
            List<Future<String>> futures = loader.execute();
            for(Future<String> future : futures){
                System.out.println(future.get());
            }
        } catch (Loader.LoaderException | InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            throw new Loader.LoaderException(e);
        } finally {
            loader.shutdown();
        }
        System.out.println("--- runConcurrencyDownload end---");
    }

    private static void runConcurrencyUpload(String dirName, String url) throws Loader.LoaderException {
        System.out.println("--- runConcurrencyUpload start ---");
        Loader loader = new ConcurrencyLoader();
        uploadDirectory(loader, dirName, url);
        try {
            List<Future<String>> futures = loader.execute();
            for(Future<String> future : futures){
                System.out.println(future.get());
            }
        } catch (Loader.LoaderException | InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            throw new Loader.LoaderException(e);
        } finally {
            loader.shutdown();
        }
        System.out.println("--- runConcurrencyUpload end ---");
    }

}
