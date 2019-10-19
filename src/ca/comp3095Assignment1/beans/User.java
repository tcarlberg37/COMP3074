/*********************************************************************************
* Project: comp3095Assignment1
* Assignment: 1
* Authors: Thomas Carlberg, Kevin Di Fonzo, Nga Le, Joel Abramson
* Student Numbers: 101155271, 101152163, 101102629, 101165088
* Date: October 20, 2019
* Description: This file contains the User class that we use to make User objects.
*********************************************************************************/
package ca.comp3095Assignment1.beans;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private String role;
    
    public User() {
    	firstName = "";
    	lastName = "";
    	email = "";
    	address = "";
    	password = "";
    	role = "";
    }
     
    public User(String firstName, String lastName, String email, String address, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.role = role;
	}
	public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
     
     
}