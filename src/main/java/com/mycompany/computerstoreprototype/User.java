/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.computerstoreprototype;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amirs
 */
public class User extends FileManagement // Inherits methods from class FileManagement
{

    private String firstname;
    private String surname;
    private String username;
    private String password;
    private String permissions;
    private String hashedPassword;
    private String status;
    private final String filename="users.txt";
    
    
    
    // Constructor
    User(String firstNameField, String surnameField, String usernameField, String passwordField, String permissionsBox, String status)
    {
        this.firstname=firstNameField;
        this.surname=surnameField;
        this.username=usernameField;
        this.password=passwordField;
        this.permissions=permissionsBox;
        this.status=status;
        
        //Hashes the user inputted password
        PasswordsAndSecurity hashPassword = new PasswordsAndSecurity(); //create instance of PasswordsAndSecurity
        this.hashedPassword = hashPassword.hashLoop(primaryKeyGen("users.txt") + password + "eHw8$n#5L@2aGt3Z"); // Input to be hashed: primary key + password, utilising the primary key as salt
    }
    
    public void saveUsers() //method to save customers to file
    {
        String input = primaryKeyGen(filename) + "," + firstname + "," + surname + "," + username + "," + hashedPassword + "," + permissions + "," + status + "\n"; //input that is to be saved
        save(input, filename); //saves input to filename using method inherited from FileManagement
    }
    
    public static String [][] usersArray() // "Reads customers.txt" to a 2D array
    {
       return readFileToArray("users.txt",7); 
    }
    
    public void editUsers(int idToEdit, int modifyPassword) //edits "customers.txt file"
    {
        if(modifyPassword == 0) //if keep original password is selected
        {
            String users[][] = usersArray();
            String input = firstname + "," + surname + "," + username + "," + users[idToEdit][4] + "," + permissions + "," + status; //input of whats to be written
            editFile(filename,7,idToEdit,input); // updates the selected row with 'input'
        }
        else //if change password is selected
        {
            PasswordsAndSecurity hashPassword = new PasswordsAndSecurity(); //create instance of PasswordsAndSecurity
            String hashedEditedPassword = hashPassword.hashLoop(idToEdit + password + "eHw8$n#5L@2aGt3Z"); //the id to edit is the salt, the user inputted password is the password then on the end is the pepper all to be hashed 1000 times
            
            String input = firstname + "," + surname + "," + username + "," + hashedEditedPassword + "," + permissions + "," + status; //input of whats to be written
            editFile(filename,7,idToEdit,input); // updates the selected row with 'input'
        }
        
    }
    
}
