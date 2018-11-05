/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab00;

/**
 *
 * @author Joon Lee
 */
public class Lab00{
   public static void main(String[] args){
       int x = 5;
       String y = "hello";
       double z = 9.8;
       
       System.out.println("x: "+ x + " y: "+ y + " z: " + z);
       
       int[]array = {3, 6, -1, 2};
       
       for (int element: array) {
            System.out.println(element);
        }
       
       int num_found = char_count(y, 'l');
       System.out.println("Found: " + num_found);
       
       for (int i = 1; i <= 10; i++){
           System.out.print(i+" ");
       }
       System.out.println();
       
   }
    public static int char_count(String s, char c){
        int count = 0;
        for (int i=0; i <s.length(); i++){
            if (s.charAt(i) == c){
                count++;
            }                    
        }
        return count;
    }
}