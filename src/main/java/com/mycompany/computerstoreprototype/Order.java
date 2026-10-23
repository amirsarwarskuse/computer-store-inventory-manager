/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.computerstoreprototype;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amirs
 */
public class Order extends FileManagement
{
    public void saveOrder(String customerID)
    {
        LocalDate date = LocalDate.now(); // Sets variable date to the current date 
        String priceTotal = priceTotal();
        String orderRecord = primaryKeyGen("orders.txt") + "," + customerID + "," + priceTotal + "," + date + "\n"; // Defines a singular record of what is to be saved in "orders.txt"
        save(orderRecord,"orders.txt"); // saves the record to file 
    }
    
    public void saveOrderItems()
    {
        //reads items file, basket file, orders file and customers file all to arrays
        String items[][] = readFileToArray("items.txt",7);
        String basket[][] = readFileToArray("basket.txt",5);
        String orders[][] = readFileToArray("orders.txt",4);
        
        
        //Define array orderItemsTemp with the same number of rows as the basekt and 4 columns
        String orderItemsTemp[][] = new String[basket.length][3];
        
        //sets the array orderItemsTemp to the corresponding values from the arrays orders and basket
        for(int i = 0 ; i < basket.length ; i++)
        {
            orderItemsTemp[i][0] = orders[orders.length-1][0]; //order id
            orderItemsTemp[i][1] = basket[i][0]; // item id
            orderItemsTemp[i][2] = basket[i][2]; // item quantity
        }
        
        
        // Reduces the quantity of "items" depending on the quantity of which were in the order/basket
        for(int i = 0; i<basket.length ; i++)
        {
            int itemsID = Integer.parseInt(basket[i][0]); // itemsID set to variable
            items[itemsID][4] = String.valueOf(Integer.parseInt(items[itemsID][4])-Integer.parseInt(basket[i][2]));
        }
        
        //saves orderItems and saves updated items
        saveArrayToFile(orderItemsTemp,"orderItems.txt",3,true);
        saveArrayToFile(items,"items.txt",7,false);
        
    }
    
    public void addToBasket(Integer selectedRow)
    {
        
        String [][] itemsArray = readFileToArray("items.txt",7); // reads items.txt to an array
        String [][] basketArray = readFileToArray("basket.txt",5); //reads basket.txt to an array
        String tempBasketArray [] = new String[5]; // define an array named tempBasketArray with 5 columns 
        boolean found = false; // creates a boolean variable, this will be used when searching the basket
        Integer locationOfRow = null; // will be used to identify row where an existing item already exists
        
        if(Integer.parseInt(itemsArray[selectedRow][4]) > 0) // If statement preventing item being added to basket if its quantity is less than 1 (TO ADD - If item quantity = basket quantity then do not add)
        {
            for (int i = 0; i < basketArray.length; i++) //loop starting from 0 to the number of rows of the basket array
            {
                if (basketArray[i][0].equals(itemsArray[selectedRow][0])) // if item id is the same as to what we are trying to add to the basket set found = true and initialise variable locationOfRow to the row where it was found 
                {
                    found = true; // item is already in basket is set to true
                    locationOfRow = i; //row location of item in basket
                }
            }
            
            if(found == true && (Integer.parseInt(itemsArray[selectedRow][4]) > Integer.parseInt(basketArray[locationOfRow][2]))) //if the quantity of the item in the basket is more than whats in stock, do not add to basket
            {
                basketArray[locationOfRow][2] = String.valueOf(Integer.parseInt(basketArray[locationOfRow][2]) + 1);
                basketArray[locationOfRow][3] = String.valueOf(Integer.parseInt(basketArray[locationOfRow][3]) + Integer.parseInt(itemsArray[selectedRow][5]));

                //save basketArray to file
                saveArrayToFile(basketArray,"basket.txt",5,false);
            }
            if(found == false)
            {
                tempBasketArray[0]=itemsArray[selectedRow][0]; // item id
                tempBasketArray[1]=itemsArray[selectedRow][1]; // item name
                tempBasketArray[2]="1";                        // item quantity
                tempBasketArray[3]=itemsArray[selectedRow][5]; // item price
                tempBasketArray[4]=itemsArray[selectedRow][6]; // item discount

                String basketRecord = tempBasketArray[0] + "," + tempBasketArray[1] + "," + tempBasketArray[2] + "," + tempBasketArray[3] + "," + tempBasketArray[4] + "\n";
                save(basketRecord,"basket.txt");
            }
        }
    }
    
    public String[][] readBasketToArray () // method that reads "basket.txt" to a 2D array
    {
        return readFileToArray("basket.txt",5);
    }
    
    public String priceTotal () //method that gets the total of all the items in the basket
    {
        double total = 0; // define total initialising it with the value of 0
        String basket[][] = readBasketToArray(); // Read "basket.txt" to a 2D array named basket
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = 0; i<basket.length; i++) //loop for every item in the array 
        {
            total = total + (Double.parseDouble(basket[i][3]) * (1 - Double.parseDouble(basket[i][4]))); // total is calculated by multiplying the quantity of an item by its price so on so on
        }
        return String.valueOf(decimalFormat.format(total)); // returns the total as a String formatted to 2 decimal points
    }
    
    public String[][] orderHistory()
    {
        
        //Define arrays basket, customers, orders and orderHistory
        String [][] customers = readFileToArray("customers.txt",6);
        String [][] orders = readFileToArray("orders.txt",4);
        String [][] orderHistory = new String[orders.length][5];
        
        for(int i = 0; i< orders.length; i++)
        {
            if(!"null".equals(orders[i][1]))
            {
                orderHistory[i][0] = orders[i][0]; //primary key (order id)
                orderHistory[i][1] = customers[Integer.parseInt(orders[i][1])][1]; //firstname element
                orderHistory[i][2] = customers[Integer.parseInt(orders[i][1])][2]; //surname element
                orderHistory[i][3] = orders[i][2]; //total element
                orderHistory[i][4] = orders[i][3]; //date element
            }
            else
            {
                orderHistory[i][0] = orders[i][0]; //primary key (order id)
                orderHistory[i][1] = "No Recorded Customer"; //firstname element
                orderHistory[i][2] = "No Recorded Customer"; //surname element
                orderHistory[i][3] = orders[i][2]; //total element
                orderHistory[i][4] = orders[i][3]; //date element
            }
        }
        return orderHistory;
    }
    public String [][] orderHistoryItems(Integer orderID)
    {
        String [][] orderItems = readFileToArray("orderItems.txt",3);
        String [][] items = readFileToArray("items.txt",7);
        
        Integer orderHistoryItemsLength = 0;
        Integer flag1 = 0;
        
        //loop to determine the lenght of orderHistoryItems
        for(int i = 0; i< orderItems.length;i++)
        {
            if(orderItems[i][0].equals(orderID.toString()))
            {
                orderHistoryItemsLength ++ ;
                
                //upon the first time where orderItems[i][0] equals the orderID the flag will become 1
                if(flag1 == 0)
                {
                    flag1 = 1;
                }
            }
            else
            {
                // if the flag has already been matched ( it = 1 ) then end the loop as there are no more comparisons required. Optimisation made. 
                if(flag1 == 1)
                {
                    break;
                }
            }
        }
        
        String [][] orderHistoryItems = new String [orderHistoryItemsLength][4]; //declaring the size of orderHistoryItems Array
        Integer flag2 = 0; // declaring the flag variable as an integer
        Integer j = 0; // this will be used for the rows of the orderHistoryItems array
        
        for(int i = 0; i < orderItems.length;i++)
        {
            // if the order id in orderItems.txt of row i matches the orderID that is clicked by the user 
            if(orderItems[i][0].equals(orderID.toString()))
            {   
                orderHistoryItems[j][0] = orderItems[i][1]; // element 1 = itemID
                orderHistoryItems[j][1] = items[Integer.parseInt(orderItems[i][1])][1]; // element 2 = item name
                orderHistoryItems[j][2] = items[Integer.parseInt(orderItems[i][1])][5]; // element 3 = item price
                orderHistoryItems[j][3] = orderItems[i][2]; // element 4 = item quantity    
                
                j++;
                
                // the first time that the order ID is matched the flag will now be set to 1
                if(flag2 == 0)
                {
                    flag2 = 1;
                }
                
            }
            else
            {
                // if the flag has already been matched ( it = 1 ) then end the loop as there are no more comparisons required. Optimisation made. 
                if(flag2 == 1)
                {
                    break;
                }
            }    
        }
        
        return orderHistoryItems;
    }
    
}
