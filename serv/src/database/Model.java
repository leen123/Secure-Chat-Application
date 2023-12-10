package database;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    public  long id=0;
    public String text="";
    public int number=0;
    public int send_id=0;
    public int rec_id=0;
    public String password="";
    public boolean checkRec=false;

public Model(){}
    public Model( int number){
        this.number=number;
    }
    public Model( String text,int number,String password, int send_id, int rec_id, boolean checkRec){
        this.number=number;
        this.text=text;
        this.send_id=send_id;
        this.rec_id=rec_id;
        this.password=password;
        this.checkRec=checkRec;
    }
    public Model(String text, int send_id, int rec_id){
        this.text=text;
        this.send_id=send_id;
        this.rec_id=rec_id;
    }
}
