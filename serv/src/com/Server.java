package com;

import database.Model;
import database.Repo;

import java.io.*;
import java.net.ServerSocket;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Server {
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
    public static void main(String[] args) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, ClassNotFoundException {
        generateKeys();
        ServerSocket serverSocket = new ServerSocket(4900);
        for (int i=0;i<5;i++){
            new ServerHandler(serverSocket).start();
        }
    }
}
