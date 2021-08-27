package Aayush.Aayush;
import java.util.*;
import java.io.*;
import Aayush.DAO.Person;
import Aayush.DBManager.DBoperations;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class Controller  {
    @GetMapping("/createTable")
    public static String createTable(@RequestParam  String name) throws SQLException {
        DBoperations.createTable(name);
        return "Table has been Created";
    }
    @RequestMapping(value="/insertPerson",method =RequestMethod.POST)
    public static String insertPerson(@RequestBody Person ob) throws Exception {
        //Receving the data in the form of the Person object
          return DBoperations.insertPerson(ob);

    }
    //Api for deleting the record
    @GetMapping("/deletePerson")
    public static String delete(@RequestParam int id) throws SQLException {
        return DBoperations.deletePerson(id);
    }
    @GetMapping("/GetPerson")
    public List<Person> fetchPerson() throws Exception {
        //Returning the data from the mysql server
        return DBoperations.getPersons();
    }
    @GetMapping("/error")
    public String error(){
        return "Error";
    }


}
