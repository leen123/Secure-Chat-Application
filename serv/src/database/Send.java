package database;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Send implements Serializable {
   public List<Model> models=new ArrayList<>();
    public boolean is=true;
    public String mac;
    public String sign;
    public int encryptType=0;
    public int servType=0;
    public byte[] iv;
    public PublicKey publicKey;
    public String sessionKey;
}
