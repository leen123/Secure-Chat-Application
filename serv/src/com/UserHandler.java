package com;

import database.Model;
import database.Send;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserHandler {
    Send send=new Send();

    UserHandler(Send send){
        this.send=send;

    }

    public Send run() {
        try {
            Socket userSocket= new Socket("localhost",4900);
            send= Security.Encryption(send);

            ObjectOutputStream outToServer = new ObjectOutputStream(userSocket.getOutputStream());
            outToServer.writeObject(send);

            ObjectInputStream inFromServer = new ObjectInputStream(userSocket.getInputStream());
            send= (Send) inFromServer.readObject();


            send= serv(send);
            if(send.is){

                send= Security.Decryption(send);



            }
            userSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return send;
    }
    Send serv(Send send){
        if(send.servType==1){
            if(send.models.get(0)==null){
                System.out.println("login fail <->");
            }else {
                System.out.println("login correct <->");
                User.id=send.models.get(0).number;
            }

        }else if(send.servType==2){
            if(!send.is){
                System.out.println("sign fail <->");
            }else {
                System.out.println("sign correct <->");
                User.id=send.models.get(0).number;
            }
        }
        else if(send.servType==3){
            System.out.println("message correct <->");
        }
        else if(send.servType==4){
            System.out.println("messages:");
            for (Model model:send.models
                 ) {
                System.out.println("send:"+model.send_id+"\tmessage:"+model.text);
            }
        }
        else if(send.servType==5){
            System.out.println("numbers:");
            for (Model model:send.models
            ) {
                System.out.println(model.number);
            }
        }
        return send;
    }
}
