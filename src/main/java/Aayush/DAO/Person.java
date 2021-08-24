package Aayush.DAO;

//MVC (Model)
public class Person {
    //Primary key

    public int id;
    private String name;
    private int age;

  //Constructor designing
    public Person(int id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;

    }

//Getter and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
