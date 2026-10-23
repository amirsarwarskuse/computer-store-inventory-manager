/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.computerstoreprototype;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amirs
 */
public class PasswordsAndSecurity 
{
    public String hash (String input)
    {
        try
        {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(input.getBytes());
            byte[] hashArray = sha256.digest();
            String hashString = new BigInteger(1, hashArray).toString(16);    
            return hashString;    
        }
        catch (NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(PasswordsAndSecurity.class.getName()).log(Level.SEVERE, null, ex);    
            return null;   
        }    
    }  
    
    public String hashLoop(String input) //
    {
        String loopedHash = null; 
        
        for(int i = 0; i < 1000; i++) // hash the input 1000 times
        {
            loopedHash = hash(input);
            input = loopedHash;
        }
        
        //return the loopedHash
        return loopedHash;
    }
}
