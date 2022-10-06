package org.macnss;

public class Person {

    protected int id;
    protected String username;
    protected String email;
    protected String password;

    public Person() {

    }

    public Person(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public Person(int id,String username,String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
