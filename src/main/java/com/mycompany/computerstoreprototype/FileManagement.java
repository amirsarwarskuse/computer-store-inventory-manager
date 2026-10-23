/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.computerstoreprototype;

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
public class FileManagement 
{
    public void save(String input, String filename) // save method that appends to the end 
    {
        try 
        {
            FileWriter fw = new FileWriter(filename, true); // Create instance of file writer class, writing to "filename" and append is true so it does not overwrite the file 
            fw.write(input); // writes and saves the variable input which is a string to file 
            fw.close(); 
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearFile(String filename) // save method that appends to the end 
    {
        try 
        {
            FileWriter fw = new FileWriter(filename, false); // Create instance of a file writer class, writing to "filename" and append is false so that the file is overwritten 
            fw.write(""); //write "" to file so that it will be cleared
            fw.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveArrayToFile(String[][] arrayName, String filename, int columns, boolean append) // method to save an array to file in a comma seperated form
    {
        try 
        {
            FileWriter fw = new FileWriter(filename, append); // Create instance of a filewriter, writing to "filename" and append is true so that it will not overwrite. 
            
            for (int i = 0; i < arrayName.length; i++)  // Loop for number of rows
            {
                for (int j = 0; j < columns; j++) // Loop for columns 
                {
                    fw.write(String.valueOf(arrayName[i][j]));
                    if (j < columns - 1) {
                        fw.write(","); // Add comma only if it's not the last column
                    }
                }
                
                fw.write("\n"); // Newline after completion of j loop ( Single record is written ) 
            }
            
            fw.close();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String [][] readFileToArray(String filename, Integer columns) // Static Method to read file to array 
    {
        Integer records = recordCalculator(filename); // Variable that holds the number of records in a file
        String[][] arrayOfReadFile = new String[records][columns]; // Define array called arrayOfReadFile with rows length of record, and columns of number of the variable columns 
        
        try 
        {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(new File(filename)));
            String currentLine;
            Integer rows = 0;
            try 
            {
                while((currentLine = lineNumberReader.readLine()) != null) //while the line being read is not empty, read each item seperated by a , to an element into arrayOfReadFile
                {
                    String temp [] = currentLine.split(","); //splits just 1 record to a 1D array 
                    for(Integer i = 0; i < columns; i++)
                    {
                        arrayOfReadFile[rows][i] = temp[i]; // Adds on the temp array to the 2D array
                    }
                    rows = rows + 1;   
                }
                lineNumberReader.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (FileNotFoundException ex) 
        { 
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayOfReadFile; //returns the arrayOfReadFile
    }
    
    
    public void editFile(String filename, Integer columns, Integer rowToWrite, String textToSave) //edit method to edit a file 
    {
        // Read file to a 2D Array
        String[][] arrayOfReadFile = readFileToArray(filename, columns); 

        // Edit Desired Record
        String[] recordToInsert = textToSave.split(","); // Splits string at every , inserting them to the array
        for (int i = 1; i < columns; i++) // Given IDs are not be changed by the user, the input wont contain 
        {
            arrayOfReadFile[rowToWrite][i] = recordToInsert[i-1];
        }

        // Overwrite File with Updated Array
        try 
        {
            FileWriter fw = new FileWriter(filename, false);
            
            for (int i = 0; i < arrayOfReadFile.length; i++)  // Loop for number of rows
            {
                for (int j = 0; j < columns; j++) // Loop for columns 
                {
                    fw.write(String.valueOf(arrayOfReadFile[i][j]));
                    if (j < columns - 1) {
                        fw.write(","); // Add comma only if it's not the last column
                    }
                }
                
                fw.write("\n"); // Newline after completion of j loop ( Single record is written ) 
            }
            
            fw.close();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Integer recordCalculator(String filename) // Static method to calculate the number of records
    {
        String currentLine; // Define variable currentLine as a String
        Integer records = 0; // Define and Initiate lineNumber as an Integer = 0 
        
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(new File(filename))); // Create an instance of a lineNumberReader called lineNumberReader that will read variable filename
            try {
                while((currentLine = lineNumberReader.readLine()) != null) // while the currentLine is not empty add 1 to the line number
                {
                    records = records + 1; 
                }
                
                lineNumberReader.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return records; // return the total line count once file has been completely read
    }
    
    
    public String primaryKeyGen(String filename) // Generated the Next Primary Key with input parameters filename. 
    {
        
        String currentLine; // Define variable currentLine as a String
        Integer lineNumber=0; // Define and Initiate lineNumber as an Integer = 0 
        
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(new File(filename))); // Create an instance of a lineNumberReader called lineNumberReader that will read variable filename
            try {
                while((currentLine = lineNumberReader.readLine())!=null) // while the currentLine is not empty add 1 to the line number
                {
                    lineNumber++;
                }
                
                lineNumberReader.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return String.valueOf(lineNumber); // return the total line count once file has been completely read
    }
}
