/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package language_id;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greg
 */
public class Probabilities
{

    int countEnglish[] = new int[26];

    Probabilities()
    {
    }

    public void getEnglish(int choice)
    {
        BufferedReader br = null;
        try
        {
            switch (choice)
            {
                case 1:
                    br = new BufferedReader(new FileReader("english.txt"));
                    break;
                case 2:
                    br = new BufferedReader(new FileReader("french.txt"));
                    break;
                default:
                    br = new BufferedReader(new FileReader("polish.txt"));
                    break;
            }


            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                line = line.toLowerCase();
                //System.out.println(line);
                getCount(line);
                line = br.readLine();
            }
            char toDispay = 'a';
            int total = 0;
            for (int i = 0; i < countEnglish.length; i++)
            {
                System.out.println("" + toDispay + " " + countEnglish[i]);
                toDispay++;
                total += countEnglish[i];
            }

            System.out.println("Total number of letters: " + total);

        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void getCount(String line)
    {
        char letter = 'a';
        for (int i = 0; i < 26; i++)
        {
            int counter = countOccurrences(line, letter);
            //System.out.println(""+letter+" count"+ counter);
            countEnglish[i] += counter;
            letter++;
        }
    }

    private int countOccurrences(String line, char letter)
    {
        int count = 0;
        for (int i = 0; i < line.length(); i++)
        {
            if (line.charAt(i) == letter)
            {
                count++;
            }
        }
        return count;
    }
}
