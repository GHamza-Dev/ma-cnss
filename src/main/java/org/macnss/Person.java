package org.macnss;

public class Person {

    private int id;
    private String username;
    private String email;
    private String password;

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
}
