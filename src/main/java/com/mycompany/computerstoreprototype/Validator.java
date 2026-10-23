package com.mycompany.computerstoreprototype;

import java.util.regex.Pattern;


public class Validator 
{
    // presence check
    public boolean presenceCheck(String input) 
    {
        String trimmedInput = input.trim();
        return trimmedInput.equals(""); // if the trimmed input is equal to "" then return true ( error is made ) 
    }

    // email check
    public boolean emailCheck(String input) 
    {
        return !input.contains("@") || !input.contains("."); // if the input does not contain @ or . then return true ( error is made ) 
    }

    // integer check
    public boolean intCheck(String input) 
    {
        try // attempt to parse the input to an integer, if it fails then an error is made
        {
            Integer intValue = Integer.parseInt(input); 
            if(intValue>0)
            {
                return false;
            }
            else
            {
                return true;
            }
        } 
        catch (NumberFormatException e) 
        {
            return true;
        }
    }

    // double check
    public boolean doubleCheck(String input) 
    {
        try // attempt to parse the input to an double, if it fails then an error is made
        {
            Double.parseDouble(input);
            return false; 
        } 
        catch (NumberFormatException e) 
        {
            return true;
        }
    }

    // phone check
    public boolean phoneCheck(String input) 
    {
        try // attempt to parse the input to an long ( some international numbers can be too long for an integer ) , if it fails then an error is made
        {
            String inputWithoutSymbols = input.replaceFirst("^0+", ""); //clear out any additionally saved characters
            
            Long longValue = Long.parseLong(inputWithoutSymbols); // attempt to convert to a long 
            if(longValue > 0)
            {
                return false; //ensures no negatives
            }
            else
            {
                return true;
            }
        } 
        catch (NumberFormatException e) 
        {
            return true;
        }
    }

    // length check
    public boolean lengthCheck(String input, int length)  //if input is more than length return errorMade
    {
        return input.length() > length;
    }

    // date check
    public boolean dateCheck (String input)
    {
        // DD/MM/YYYY   
        try
        {
        String dayString = input.substring(0,2);
        String monthString = input.substring(3,5); // sets month (BI Inclusive, EI exclusive)
        String yearString = input.substring(6,8);
        Integer day = null;
        Integer month = null;
        Integer year = null;
        try //try parse string to and int if characters are found it will be unable to and exception will be caught
        {
            day = Integer.parseInt(dayString);
            month = Integer.parseInt(monthString);
            year = Integer.parseInt(yearString);
        }
        catch(NumberFormatException e)
        {
            return true;
        }
        
        //Checks if less than or equal to 0
        if(day <= 0 || month <= 0 || year <= 0)
        {
            return true;
        }
        
        //checks if days exceed 31
        if(day > 31)
        {
            return true;
        }
        
        //Checks if days exceed 30 for months that do not contain 31 days excluding February
        if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))
        {
            return true;
        }
        
        //Checks if it is a leap year, if not, February cannot have greater than 28 days
        if (year % 4 != 0 && month == 2 && day > 28)
        {
            return true;
        }
        
        //Checks February does not have more than 29 days
        if (month == 2 && day > 29)
        {
            return true;
        }
        return false;
        }
        catch(StringIndexOutOfBoundsException e)
        {
            return true;
        }
    }
    
    // validation for username uniqueness
    public boolean uniqueUsernameCheck(String input) 
    {
        String [] searchedValue = SortsAndSearches.linearSearch("users.txt", input, 7, 3); // Linear search on file users.txt, use linear search as file is not sorted.
        
        if(searchedValue != null) // if the search returns no result then error is not made, if it does return a result the username is found so error is made
        {
            return true;
        }
        
        return false;
    }
    
    // validation for password
    public boolean passwordCheck(String input) 
    {
        String conditions = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",.]).{8,}$"; // ensures password is more than or equal to 8 characters, contains 1 number, contains 1 symbol and 1 capitol letter.
        if(Pattern.matches(conditions, input) && input.length() >= 8)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    // validation for , 
    public boolean commaBlock(String input) 
    {
        return input.contains(","); //if a , is found then return errorMessage
    }
    
    public boolean priceCheck(String input) // if price has more than 2 decimal places return an error
    {
        String priceFormat = "^\\d+(\\.\\d{1,2})?$";
        Pattern pattern = Pattern.compile(priceFormat);
        if(pattern.matcher(input).matches())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
