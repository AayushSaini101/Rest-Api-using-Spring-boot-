package Aayush.DBManager;


import Aayush.DAO.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBoperations {

    //Using the concept of the multithreading
    //To avoid again and again creating connection
    private static volatile Connection connection;
    //Creating the connection the server
    public static Connection getConnection() throws SQLException {

  //sdfsdf

        if(connection==null){
            synchronized (DBoperations.class){
                //Creating connection to the standalone mysql server
                if(connection==null) {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person","root","Aayush");
                }
                else{
                    return connection;
                }

            }
        }
        else {
            //Returning the Server
            return connection;
        }
        return connection;

    }
    DBoperations() throws SQLException {
        getConnection();
    }
    //Function to creating the table
    public static void createTable(String name) throws SQLException {
        getConnection();
        Statement st=connection.createStatement();
        String tablename=name;
        st.execute("Create table "+tablename+"(id integer primary key,name varchar(30) not null ,ade int not null);");


    }
    //Closing the server connection
    public static void closeConnection() throws SQLException {
        //Checking the server
        if(connection!=null){
            //Synchronized the multiple thread
            synchronized (DBoperations.class){

               if(connection!=null){
                   //Closing the connection
                   connection.close();

               }

            }
        }

    }

    public static String insertPerson(Person ob) throws Exception{
         getConnection();
         PreparedStatement st = connection.prepareStatement("Insert into Person values(? ,? ,?);");
         st.setInt(1,ob.getId());
         st.setString(2,ob.getName());
         st.setInt(3,ob.getAge());
      int rows=st.executeUpdate();
      if(rows>0){
          return "Created";
      }
      else{
          return "Problem occured";
      }




    }
    public static String deletePerson(int id) throws SQLException {
        getConnection();
        //Creating DML statements
        PreparedStatement st=connection.prepareStatement("delete from person where id =?;");
        st.setInt(1,id);
        //Checking the rows affected
        int row=st.executeUpdate();
        if(row>0){
            return "Deleted Successfull";
        }
        else{
            return "Error";
        }

    }
    public static List<Person> getPersons() throws Exception{
      getConnection();
      Statement st=connection.createStatement();
      ResultSet ans=st.executeQuery("Select * from Person;");
      List<Person>P=new ArrayList<Person>();
      while(ans.next()){
          String id=ans.getString(1);
          String name=ans.getString(2);
          String age=ans.getString(3);
        //Creating tempory object;
          Person ob=new Person(Integer.parseInt(id),name,Integer.parseInt(age));
          P.add(ob);

      }
      return P;

    }


}
