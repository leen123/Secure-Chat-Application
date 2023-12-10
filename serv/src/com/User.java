package com;

import database.Model;
import database.Send;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class User {
    static Scanner io =new Scanner(System.in);
    static int id =0;
    static  public PrivateKey privateKey;
    static  public PublicKey publicKey;
    private  static void generateKeys() throws NoSuchProviderException, NoSuchAlgorithmException, IOException, ClassNotFoundException {
        GenerateKeys generateKeys= new GenerateKeys();
        generateKeys.createKeys();
        FileInputStream fileInputStream= new FileInputStream("file/publicKey.dat");
        ObjectInput ois = new ObjectInputStream(fileInputStream);
        publicKey = (PublicKey) ois.readObject();
        ois.close();
        if(publicKey==null){
            publicKey = generateKeys.getPublicKey();
            FileOutputStream fileOutputStream = new FileOutputStream("file/publicKey.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(publicKey);
        }
        fileInputStream = new FileInputStream("file/privateKey.dat");
        ois = new ObjectInputStream(fileInputStream);
        privateKey = (PrivateKey) ois.readObject();
        ois.close();
        if(privateKey==null){
            privateKey = generateKeys.getPrivateKey();
            FileOutputStream fos = new FileOutputStream("file/privateKey.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(privateKey);
        }
    }

    public static void main(String[] args) {
        Send send=new Send();
        System.out.println("encrypt : 1-zero \t 2-CBC \t 3-PGP \t 4-sing \t 5-ca");
        System.out.print("E : ");
        int encrypt=io.nextInt();
        send.encryptType=encrypt;
        System.out.println("serv :\n 1-login \n  2-sign");
        System.out.print("E : ");
        int serv=io.nextInt();
        send.servType=serv;
        send=serv1(send);
        if(send.is) send=new UserHandler(send).run();
        send.models.clear();
        while (true){
            System.out.println("serv :\n 3-sendMessage \n  4-getAllMessages \n  5-getNumbers");
            System.out.print("E : ");
            serv=io.nextInt();
           send.servType=serv;
           send=serv2(send);
            send=new UserHandler(send).run();
            send.models.clear();
        }

    }
   static Send serv1(Send send){
        Model model=new Model();
        if(send.servType==1){
            System.out.println("login <->");
            System.out.print("Number:");
            model.number=io.nextInt();
            io.nextLine();
            System.out.print("Password:");
            model.password=io.nextLine();
        }else if(send.servType==2){
            System.out.println("sign <->");
            System.out.print("Number:");
            model.number=io.nextInt();
            io.nextLine();
            System.out.print("Password:");
            model.password=io.nextLine();
        }
        send.models.add(model);
        return send;
    }
   static Send serv2(Send send){
        Model model=new Model();
        if(send.servType==3){
            System.out.println("message <->");
            System.out.print("Number:");
            model.send_id=id;
            model.rec_id=io.nextInt();
            io.nextLine();
            System.out.print("message:");
            model.text=io.nextLine();
        }
        else if(send.servType==4){
            System.out.println("messages <->");
            System.out.print("Number:");
            model.send_id=id;
            model.rec_id=io.nextInt();
        }
        else if(send.servType==5){
            model.send_id=id;
        }
        send.models.add(model);
        return send;
    }
}
