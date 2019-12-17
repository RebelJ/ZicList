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
            while (socketClient.isConnected()){
                this.socketClient.getInputStream().read(message);

                if (message.substring(0, 6).startsWith("sync://")){
                    String session = message.substring(6, message.length());

                    while (true){
                        //url = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
                        String url = "";
                        if (url.substring(0, 6).startsWith("http://")){
                            //read.url(session, url)
                            // write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING));
                        }else break;
                    }
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


}
