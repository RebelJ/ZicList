package com.example.ziclist;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class ConnectionServer extends Thread{

    private Socket socketClient;
    private ServerSocket socket;
    public static final int PORT = 4567;

    private static final ConnectionServer ourInstance = new ConnectionServer(PORT);

    static ConnectionServer getInstance() {
        return ourInstance;
    }

    private ConnectionServer(int port) {
        try {
            this.socket = new ServerSocket(port);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private class ConnectServerAsyncTask extends AsyncTask<Void, Void, Void> {
        Exception e;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                socket = new ServerSocket(PORT);
                //socketClient = socket.accept();

            }catch (Exception ex) {
                ex.printStackTrace();
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

    public void run(){
        try {
            socketClient = socket.accept();
            echangeClient();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void echangeClient(){
        String message = null;
        try {
            this.socketClient.getInputStream().read(message);

            if (message.startsWith("sync://")){
                //Conect to the session

            }
            else if (message.startsWith("http://")){
                //Get the URL
                this.socketClient.getOutputStream().write(message);
            }
            else if (message.startsWith("end://")){
                //End the connection

            }

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


}
