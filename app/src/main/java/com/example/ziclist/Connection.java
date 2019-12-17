package com.example.ziclist;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

class Connection {
    private static final Connection ourInstance = new Connection();

    static Connection getInstance() {
        return ourInstance;
    }

    private Socket socket;
    private InputStream stream;
    public static String IP = "172.1.23.235";
    public static int PORT = 4567;
    private final static String ENCODING = "UTF-8";

    private BufferedReader bufferReader;
    private BufferedWriter bufferWriter;

    public void connect() {
        new ConnectAsyncTask().execute();
    }

    public void write(String message){
        try {
            new WriteAsyncTask().execute(message);
        }catch (Exception ex){
            ex.fillInStackTrace();
        }
    }

    public String read(){
        String message = null;
        try {
            InputStream is = socket.getInputStream();
            bufferReader= new BufferedReader(new InputStreamReader(is));
            message = bufferReader.readLine();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return message;
    }

    private class ConnectAsyncTask extends AsyncTask<Void, Void, Void> {
        Exception e;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(IP, PORT), 3000);

                bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
                bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING));

            }catch (Exception ex) {
                e = ex;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(e != null){
                e.printStackTrace();
            }
        }

    }

    private class WriteAsyncTask extends AsyncTask<String, Void, Void> {
        Exception e;

        @Override
        protected Void doInBackground(String... messages) {
            try {
                bufferWriter.write(messages[0], 0, messages[0].length());
                bufferWriter.newLine();
                bufferWriter.flush();

            }catch (Exception ex) {
                e = ex;
            }
            return null;
        }
    }
}
