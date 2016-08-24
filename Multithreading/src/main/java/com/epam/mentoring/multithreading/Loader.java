package com.epam.mentoring.multithreading;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Vitali_Sheuka on 8/22/2016.
 */
public abstract class Loader {

    protected final LoadListener mListener;

    public interface LoadListener {
        void onLoad(String msg);
        void onError(LoaderException e);
    }

    public static class LoaderException extends Exception {
        public LoaderException(String message) {
            super(message);
        }

        public LoaderException(Throwable cause) {
            super(cause);
        }
    }

    protected String upload(final File from, final String to) {
        String result = null;
        int serverResponseCode = 0;

        HttpURLConnection con;
        DataOutputStream dos;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        try {
            FileInputStream fis = new FileInputStream(from);
            URL url = new URL(to);
            con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);//Allow Inputs
            con.setDoOutput(true);//Allow Outputs
            con.setUseCaches(false);//Don't use a cached Copy
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "close");
            con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            con.setRequestProperty("uploaded_file", from.getAbsolutePath());

            dos = new DataOutputStream(con.getOutputStream());

            //writing bytes to data outputstream
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + from.getAbsolutePath() + "\"" + lineEnd);

            dos.writeBytes(lineEnd);

            //returns no. of bytes present in fileInputStream
            bytesAvailable = fis.available();
            //selecting the buffer size as minimum of available bytes or 1 MB
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            //setting the buffer as byte array of size of bufferSize
            buffer = new byte[bufferSize];

            //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
            bytesRead = fis.read(buffer, 0, bufferSize);

            //loop repeats till bytesRead = -1, i.e., no bytes are left to read
            while (bytesRead > 0) {
                //write the bytes read from inputstream
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fis.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fis.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = con.getResponseCode();
            String serverResponseMessage = con.getResponseMessage();

            System.out.println("Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

            if (serverResponseCode == 200) {
                result = "File Upload completed. You can see the uploaded file here:" + "/uploads/" + from.getName();
                if (mListener != null) {
                    mListener.onLoad(result);
                }
            } else {
                result = "Server Response is: " + serverResponseMessage + ": " + serverResponseCode;
                if (mListener != null) {
                    mListener.onError(new LoaderException(result));
                }
            }
            fis.close();
            dos.flush();
            dos.close();

        } catch (IOException e) {
            result = this + ":" + e.getMessage();
            if (mListener != null) {
                mListener.onError(new LoaderException(result));
            }
        }
        return result;
    }

    protected String download(final String from, final File to) {
        String result = null;
        int serverResponseCode = 0;

        HttpURLConnection con;
        DataInputStream dis = null;
        int bytesRead;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        try {
            FileOutputStream fos = new FileOutputStream(to);
            URL url = new URL(from);
            con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);//Allow Inputs
            con.setDoOutput(false);//Allow Outputs
            con.setUseCaches(false);//Don't use a cached Copy
            con.setRequestMethod("GET");
            con.setRequestProperty("Connection", "close");

            serverResponseCode = con.getResponseCode();
            String serverResponseMessage = con.getResponseMessage();

            //System.out.println("Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

            if (serverResponseCode == 200) {
                dis = new DataInputStream(con.getInputStream());

                buffer = new byte[maxBufferSize];

                bytesRead = dis.read(buffer, 0, maxBufferSize);

                while (bytesRead > 0) {
                    fos.write(buffer, 0, bytesRead);
                    bytesRead = dis.read(buffer, 0, maxBufferSize);
                }
                result = "File download completed. You can see the downloaded file here:" + to.getAbsolutePath();
                if (mListener != null) {
                    mListener.onLoad(result);
                }
            } else {
                result = "Server Response is: " + serverResponseMessage + ": " + serverResponseCode;
                if (mListener != null) {
                    mListener.onError(new LoaderException(result));
                }
            }
            if (dis!=null) dis.close();
            fos.flush();
            fos.close();

        } catch (IOException e) {
            result = this + ":" + e.getMessage();
            if (mListener != null) {
                mListener.onError(new LoaderException(result));
            }
        }
        return result;
    }

    public abstract void uploadFile(File from, String to);
    public abstract void downloadFile(String from, File to);
    public abstract List<Future<String>> execute() throws LoaderException;
    public abstract void shutdown() throws LoaderException;

    public Loader(LoadListener listener) {
        mListener = listener;
    }

    public Loader() {
        mListener = null;
    }
}
