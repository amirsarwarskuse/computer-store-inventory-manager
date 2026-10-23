/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.computerstoreprototype;



/**
 *
 * @author amirs
 */
public class Customer extends FileManagement // Inherits methods from class FileManagement
{

    private String firstname;
    private String surname;
    private String email;
    private String phone;
    private String dateCreated;
    private final String filename="customers.txt";
    
    
    
    // Constructor
    Customer(String firstNameField, String surnameField, String emailField, String phoneField, String dateCreatedField)
    {
        this.firstname=firstNameField;
        this.surname=surnameField;
        this.email=emailField;
        this.phone=phoneField;
        this.dateCreated=dateCreatedField;

        
    }
    
    public void saveCustomers() //method to save customers to file
    {
        String input = primaryKeyGen(filename) + "," + firstname + "," + surname + "," + email + "," + phone + "," + dateCreated + "\n"; //input that is to be sved
        save(input, filename); //saves input to filename using method inherited from FileManagement
    }
    public static String [][] customersArray() // "Reads customers.txt" to a 2D array
    {
       return readFileToArray("customers.txt",6); 
    }
    public void editCustomers(int idToEdit) //edits "customers.txt file"
    {
        String input = firstname + "," + surname + "," + email + "," + phone + "," + dateCreated; //input of whats to be written
        editFile(filename,6,idToEdit,input); // updates the selected row with 'input'
    }
    
}
