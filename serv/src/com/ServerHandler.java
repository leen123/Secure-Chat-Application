package com;


import database.Repo;
import database.Send;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ServerHandler extends Thread{
    ServerSocket server = null;
    Socket client;
    ServerHandler(ServerSocket server){
        this.server=server;
    }
    ServerHandler(){
    }
    Repo repo=new Repo();
    @Override
    public void run() {
        while (true) {
            try {
                client = server.accept();
                ObjectOutputStream outToClient = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
                //rec
                Send send = (Send) inFromClient.readObject();

                //map= com.Security.Decryption((String) map.get("securityType"),map);


                if(send.is){
                    send= serv(send);
                    //msg = controllerRequest.processService(msg);

                    //send
                    //map= com.Security.Encryption((String) map.get("securityType"),map);

                }



                outToClient.writeObject(send);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (client != null)
                    try {
                        client.close();
                    } catch (IOException  e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    Send serv(Send send){
        if(send.servType==1){
            send.models.set(0,repo.logIn(send.models.get(0).number,send.models.get(0).password));
        }else if(send.servType==2){
            send.is=(repo.signUp(send.models.get(0)));
        }
        else if(send.servType==3){
            send.is=(repo.saveMessage(send.models.get(0)));
         repo.saveNumber(send.models.get(0));
        }
        else if(send.servType==4){
            List models=(repo.getAllMessagesByClient(send.models.get(0).send_id,send.models.get(0).rec_id));
            send.models=models;
        }
        else if(send.servType==5){
            List models=(repo.getNumbersByNumber(send.models.get(0).number));
            send.models=models;
        }
        return send;
    }


}
