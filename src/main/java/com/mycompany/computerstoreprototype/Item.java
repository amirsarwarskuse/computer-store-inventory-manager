package com.mycompany.computerstoreprototype;
/**
 *
 * @author amirs
 */

// Initialising Variables
public class Item extends FileManagement // Inherits methods from class FileManagement
{
    private String itemName;
    private String brandName;
    private String itemType;
    private String quantity;
    private String price;
    private String discount;
    private final static String filename="items.txt";
    private Integer rowToWrite;
    
    // Constructor requiring the following variables below
    Item(String itemNameField, String brandNameField, String itemTypeField, String quantityField, String priceField, String discountField, Integer selectedRow)
    {
        itemName=itemNameField;
        brandName=brandNameField;
        itemType=itemTypeField;
        quantity=quantityField;
        discount=discountField;
        price=priceField;
        rowToWrite=selectedRow;
    }
    
    // Save Method using method from FileManagement 
    public void saveItems()
    {
        String input = primaryKeyGen(filename) + "," + itemName + "," + brandName + "," + itemType + "," + quantity + "," + price + "," + discount + "\n"; // Input of what is to be saved
        save(input,filename); // Saves the input to file
    }

    // Method that reads "items.txt" to a 2D array
    public static String [][] itemsArray()
    {
        return readFileToArray(filename,7); // Reads "items.txt" to an array and returns it
    }
    
    // Edit method using method from FileManagement
    public void edit() 
    {
        String input = itemName + "," + brandName + "," + itemType + "," + quantity + "," + price + "," + discount; // Sets what is to be saved
        editFile(filename,7,rowToWrite,input); // updates the selected row with 'input'
    }  
    
}

