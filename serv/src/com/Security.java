package com;

import database.Send;

import javax.crypto.Mac;
import java.security.Key;
import java.util.Map;

public class Security {
    public static String MAC(Key key,Object data) throws Exception {

        Mac mac = Mac.getInstance("HMACSHA1");
        mac.init(key);
        mac.update(data.toString().getBytes());
        byte[] macResult = mac.doFinal();

          // System.out.println(new String(macResult));
        return new String(macResult);
    }
    public static String getKeyClient(int number,String password){
        try {
            String keyClient = "Symmetric.generateStorngPasswordHash(password)";

            return keyClient;
        }catch (  Exception e){

        }
      return null;

    }
    public static Send Encryption(Send send){
        if(send.is) return send;
        switch (send.encryptType){
            case 1:
                return send;
            case 2:
                return send;
            case 3:
                return  send;
            case 5:
                return send;
            default:
                return send;
        }

    }
    public static Send Decryption(Send send){
        if(send.is) return send;
        switch (send.encryptType){
            case 1:
                return send;
            case 2:
                return send;
            case 3:
                return  send;
            case 5:
                return send;
            default:
                return send;
        }

    }

}
