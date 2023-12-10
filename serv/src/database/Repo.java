package database;



import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repo {

    Statement statement = null;
    Connection connection = null;
    public boolean signUp(Model model){
           try {
               connect();
               ResultSet resultExitNumber = statement.executeQuery("SELECT * FROM `client` WHERE num= "+ model.number+" ");
               if(resultExitNumber.next())
                   return false;
               else {
                   int res= statement.executeUpdate("INSERT INTO client (num,password) VALUES ( '"+model.number+"','"+model.password+"')",Statement.RETURN_GENERATED_KEYS);
                   return true;
               }
           } catch (SQLException throwables) {
              // System.out.println(throwables);
           } catch (NoSuchAlgorithmException e) {
               //System.out.println(e);
           } catch (InvalidKeySpecException e) {
               //System.out.println(e);
           }catch (Exception e) {
               //e.printStackTrace();
               //System.out.println(e);
           }
       return false;
   }
     public Model logIn(int number,String password){
        try {
            connect();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `client` WHERE num= "+ number+" And "+"password= \""+ password+"\"");
            if(resultSet.next())
                return new Model( resultSet.getInt(1));
            else {
                return null;
            }
        } catch (SQLException throwables) {
            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return null;
    }
     public boolean saveNumber(Model model){
        try {
            connect();

            ResultSet resultExitClientNumber = statement.executeQuery("SELECT * FROM `client` WHERE num= "+ model.rec_id);
            statement = connection.createStatement();
            ResultSet resultExitNumber = statement.executeQuery("SELECT * FROM `client` WHERE num= "+ model.send_id);
            statement = connection.createStatement();
             ResultSet resultExitClientNumberAndNumber = statement.executeQuery("SELECT * FROM `nums` WHERE client_num= "+ model.send_id+" AND "+"num= "+ model.rec_id);
            if(!resultExitClientNumber.next())
                return false;
           else if(!resultExitNumber.next())
                return false;
            else if(resultExitClientNumberAndNumber.next())
                return false;
            else {
                statement = connection.createStatement();

                model.id=(model.number+new Date(System.currentTimeMillis()).getTime())/999;
                int res= statement.executeUpdate("INSERT INTO nums (num_id ,client_num,num) VALUES ( '"+model.id+"','"+model.send_id+"','"+model.rec_id+"')",Statement.RETURN_GENERATED_KEYS);
                return true;
            }
        } catch (SQLException throwables) {
            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return false;
    }
     public List<Model> getNumbersByNumber(int number){
        try {
            connect();

            ResultSet resultSet = statement.executeQuery("SELECT client.num FROM `nums`,client WHERE client.num=nums.num And client_num= "+ number);

            List<Model> moduleList=new ArrayList<>();
            while(resultSet.next())
                moduleList.add(new Model( resultSet.getInt(1)));
            return null;

        } catch (SQLException throwables) {
            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return null;
    }
     public boolean saveMessage(Model messageModel){
        try {
            connect();
            ResultSet resultExitSend_id = statement.executeQuery("SELECT * FROM `client` WHERE num= "+ messageModel.send_id);
            statement = connection.createStatement();
            ResultSet resultExitRec_id = statement.executeQuery("SELECT * FROM `client` WHERE num= "+ messageModel.rec_id);
            if(!resultExitSend_id.next())
                return false;
            else if(!resultExitRec_id.next())
                return false;
            else {
                statement = connection.createStatement();

                messageModel.id=(messageModel.rec_id+new Date(System.currentTimeMillis()).getTime())/999;
                int res= statement.executeUpdate("INSERT INTO message (message_id,send_id,rec_id,text,checkRec) VALUES ( '"
                        +messageModel.id+"','"+messageModel.send_id+"','"+messageModel.rec_id
                        +"','"+messageModel.text+"',"+messageModel.checkRec
                        +")",Statement.RETURN_GENERATED_KEYS);
                return true;
            }
        } catch (SQLException throwables) {

            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return false;
    }
     public List<Model> getAllMessagesByClient(int send_id,int rec_id){
        try {
            connect();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `message` WHERE (send_id= "+ send_id+" And "+"rec_id= "+ rec_id+") OR (send_id= "+ rec_id+" And "+"rec_id= "+ send_id+") ");
            List<Model> moduleList=new ArrayList<>();
            while(resultSet.next())
                moduleList.add(new Model(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4)));
            return moduleList;

        } catch (SQLException throwables) {
            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return new ArrayList<>();
    }
     public List<Model> getSendingClientBySend_idAndRec_idAndCheck_rec(int send_id,int rec_id,boolean checkRec){
        try {
            connect();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `message` WHERE (send_id= "+ send_id+" And "+"rec_id= "+ rec_id+" And checkRec= "+ checkRec+") ");
            List<Model> moduleList=new ArrayList<>();
            while(resultSet.next())
                moduleList.add(new Model(resultSet.getString(1),resultSet.getInt(2),resultSet.getInt(3)));
            return null;

        } catch (SQLException throwables) {
            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return null;
    }
     public boolean updateMessage(Model messageModel){
        try {
            connect();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `message` WHERE message_id= "+ messageModel.id);
            if(!resultSet.next())
                return false;
            else {
                statement = connection.createStatement();
                int res= statement.executeUpdate("update  message set "
                +"text = '"+messageModel.text
                        +",checkRec = "+messageModel.checkRec
                        +"' where message_id = "+messageModel.id);
                return true;
            }
        } catch (SQLException throwables) {
            // System.out.println(throwables);
        } catch (NoSuchAlgorithmException e) {
            //System.out.println(e);
        } catch (InvalidKeySpecException e) {
            //System.out.println(e);
        }catch (Exception e) {
            //e.printStackTrace();
            //System.out.println(e);
        }
        return false;
    }


    public boolean connect() throws  Exception {
        String database="jdbc:mysql://localhost:3306/iss";
        String userName="root";
        String password="";

        if(statement!=null) { return  true;}
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    database, userName, password
            );

            statement = connection.createStatement();
            return  true;
        }catch (SQLException throwables) {
            throw  throwables;

           // throwables.printStackTrace();
        }
        catch (Exception e) {
            throw  e;
            //e.printStackTrace();
            //System.out.println(e);
            }

    }
}
