/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.computerstoreprototype;

/**
 *
 * @author amirs
 */
public class SortsAndSearches extends FileManagement 
{
    public static String[][] quickSortInitialiser(String[][] array, int columnToSort, boolean ascending) 
    {
        quickSort(array, 0, array.length - 1, columnToSort, ascending);
        return array; // Return the sorted 2D array
    }

    private static void quickSort(String[][] array, int low, int high, int sortByIndex, boolean ascending) 
    {
        if (low < high) 
        {
            int pivotIndex = partition(array, low, high, sortByIndex, ascending);

            // Recursively part of algorithm (recall itself)
            quickSort(array, low, pivotIndex - 1, sortByIndex, ascending);
            quickSort(array, pivotIndex + 1, high, sortByIndex, ascending);
        }
    }

    private static int partition(String[][] array, int low, int high, int sortByIndex, boolean ascending) 
    {
        String pivot = array[high][sortByIndex];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            String currentValue = array[j][sortByIndex];
            int comparisonResult;

            // Compare elements based on their data type
            if (isNumeric(currentValue) && isNumeric(pivot)) 
            {
                // If both elements are numeric, compare as numbers
                comparisonResult = Double.compare(Double.parseDouble(currentValue), Double.parseDouble(pivot));
            } 
            else 
            {
                // Otherwise, compare as strings (case-insensitive)
                comparisonResult = currentValue.compareToIgnoreCase(pivot);
            }

            if ((ascending && comparisonResult <= 0) || (!ascending && comparisonResult >= 0)) 
            {
                // Swap arr[i] and arr[j]
                i++;
                String[] temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        String[] temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // Helper method to check if a string represents a numeric value
    private static boolean isNumeric(String str) 
    {
        try 
        {
            Double.parseDouble(str);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
    }
    
    public static String[] binarySearch(String[][] arrayToSearch, String searchValue, int columns, int columnToSearch) 
    {
        return binarySearcher(arrayToSearch, searchValue, columns, 0, arrayToSearch.length - 1, columnToSearch);
    }

    private static String[] binarySearcher(String[][] arrayToSearch, String searchValue, int columns, int low, int high, int columnToSearch) // bugs: Quicksort sorting wrong column
    {
        if (low <= high) 
        {
            int midpoint = low + (int)((high - low) / 2.0);
           
            if (arrayToSearch[midpoint][columnToSearch].equals(searchValue)) 
            {
                return arrayToSearch[midpoint];
                
            } 
            else if (searchValue.compareToIgnoreCase(arrayToSearch[midpoint][1]) < 0) 
            {
                return binarySearcher(arrayToSearch, searchValue, columns, low, midpoint - 1,columnToSearch);
            } 
            else 
            {
                return binarySearcher(arrayToSearch, searchValue, columns, midpoint + 1, high,columnToSearch);
            }
        }
        
        return null;
    }
    
    public static String[] linearSearch(String filename, String searchValue, int columns, int columnToSearch) // linear search that takes the filename, search value, number of columns of the file and the column of which you want to search
    {
        String[][] arrayToSearch = readFileToArray(filename,columns); //read the file you are going to search to a 2d array
 
        for(int i = 0; i<arrayToSearch.length; i++) // for loop, starting from the first row of the array to the last row
        {
            if(searchValue.equals(arrayToSearch[i][columnToSearch])) // if the search value = the value of row: i, and column: columnToSearch, return the whole row
            {
                return arrayToSearch[i];
            }
        }
        return null;
    }
}


