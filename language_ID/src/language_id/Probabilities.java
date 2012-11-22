/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package language_id;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greg
 */
public class Probabilities
{

    final int LETTERS = 26;
    final float SMOOTH = 0.5f;
    float countSequenceEnglish[][] = new float[LETTERS][LETTERS];
    float countSequenceFrench[][] = new float[LETTERS][LETTERS];
    float countSequencePolish[][] = new float[LETTERS][LETTERS];
    float countSequenceEnglishProb[][] = new float[LETTERS][LETTERS];
    float countSequenceFrenchProb[][] = new float[LETTERS][LETTERS];
    float countSequencePolishProb[][] = new float[LETTERS][LETTERS];

    Probabilities()
    {
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
            {
                countSequenceEnglish[i][j] = SMOOTH;
                countSequenceFrench[i][j] = SMOOTH;
                countSequencePolish[i][j] = SMOOTH;

                countSequenceEnglishProb[i][j] = 0;
                countSequenceFrenchProb[i][j] = 0;
                countSequencePolishProb[i][j] = 0;
            }
        }
    }

    public void getEnglish()
    {
        BufferedReader engligh = null;
        BufferedReader french = null;
        BufferedReader polish = null;
        try
        {

            engligh = new BufferedReader(new FileReader("english.txt"));
            french = new BufferedReader(new FileReader("french.txt"));
            polish = new BufferedReader(new FileReader("polish.txt"));

            String line = engligh.readLine();
            String englishText = "";
            while (line != null)
            {
                line = line.toLowerCase();
                // Append the next line and remove everything except letters a-z
                englishText += line.replaceAll("[^A-Za-z]+", "");
                line = engligh.readLine();
            }
            
            analyzeEnglishText(englishText);

            for (int i = 0; i < LETTERS; i++)
            {
                for (int j = 0; j < LETTERS; j++)
                {
                    System.out.print(countSequenceEnglish[i][j] + " ");
                }
                System.out.print("\n");
            }

            //finding probabilities
            for (int i = 0; i < LETTERS; i++)
            {
                for (int j = 0; j < LETTERS; j++)
                {
                    countSequenceEnglishProb[i][j] = countSequenceEnglish[i][j] / (englishText.length() - 1 + LETTERS * LETTERS);
                }
            }
            
            for (int i = 0; i < LETTERS; i++)
            {
                for (int j = 0; j < LETTERS; j++)
                {
                    System.out.print(countSequenceEnglishProb[i][j] + " ");
                }
                System.out.print("\n");
            }


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
                engligh.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void analyzeEnglishText(String text)
    {
        for (int i = 1; i < text.length(); i++)
        {
            countSequenceEnglish[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
            //System.out.println(text.charAt(i) + " " + text.charAt(i - 1) + " " + (text.charAt(i) - 97) + " " + (text.charAt(i - 1) - 97));
        }
    }

    private void analyzeFrenchText(String text)
    {
        for (int i = 1; i < text.length(); i++)
        {
            countSequenceFrench[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
            //System.out.println(text.charAt(i) + " " + text.charAt(i - 1) + " " + (text.charAt(i) - 97) + " " + (text.charAt(i - 1) - 97));
        }
    }

    private void analyzePolishText(String text)
    {
        for (int i = 1; i < text.length(); i++)
        {
            countSequencePolish[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
            //System.out.println(text.charAt(i) + " " + text.charAt(i - 1) + " " + (text.charAt(i) - 97) + " " + (text.charAt(i - 1) - 97));
        }
    }
}
