/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day02;

import java.io.*;
import java.util.*;

/*
                Advent of Code
    Program title:         Password Philosophy 2
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2020/12/2
    Description:           
 */
public class PasswordPhilosophy2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nListLength, nValidCount = 0;

        //Getting the entries from a .txt file
        ArrayList<String> arListnEntries = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day02_Input.txt"));
            String word;
            while ((word = br.readLine()) != null) {
                arListnEntries.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        nListLength = arListnEntries.size();

        //Transferring the arraylist items to an array.
        String[] arstrEntries = arListnEntries.toArray(new String[nListLength]);

        //Checking if the password on the line is valid.
        for (int i = 0; i < arstrEntries.length; i++) {
            if (returnValid(arstrEntries[i])) {
                nValidCount++;
            }
        }
        
        //Output with extra info
//        System.out.println("The number of valid passwords: " + nValidCount);
        
        //Puzzle output
        System.out.println(nValidCount);
    }

    public static boolean returnValid(String strPolPass) {
        int nPos1, nPos2, nCount = 0;
        char cLetter, cTemp;
        String strPass;

        //Splitting the line into min/max, letter and the actual password.
        String[] arstrComp = strPolPass.split(" ", 3);

//        System.out.println(Arrays.toString(arstrComp));                         //TEST
        
        String[] arstrMinMax = arstrComp[0].split("-");
        nPos1 = Integer.parseInt(arstrMinMax[0]) - 1;
        nPos2 = Integer.parseInt(arstrMinMax[1]) - 1 ;

//        System.out.println(Arrays.toString(arstrMinMax));                       //TEST

        cLetter = arstrComp[1].charAt(0);

//        System.out.println("char: " + cLetter);                                 //TEST

        strPass = arstrComp[2];

        //Finding the number of times the character appears in the password at the designated spots.
        for (int i = 0; i < strPass.length(); i++) {
            cTemp = strPass.charAt(i);
            
            if (i == nPos1 || i == nPos2) {
                if (cTemp == cLetter) {
                    nCount++;
                }
            }
        }

        //Checking if the letter appears at only one the designated spots.
        if (nCount == 1) {
            return true;
        } else {
            return false;
        }
    }

}
