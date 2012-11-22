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

    public void readTexts()
    {
        BufferedReader engligh = null;
        BufferedReader french = null;
        BufferedReader polish = null;
        try
        {

            engligh = new BufferedReader(new FileReader("english.txt"));
            french = new BufferedReader(new FileReader("french.txt"));
            polish = new BufferedReader(new FileReader("polish.txt"));

            String lineEnglish = engligh.readLine();
            String englishText = "";
            while (lineEnglish != null)
            {
                lineEnglish = lineEnglish.toLowerCase();
                // Append the next line and remove everything except letters a-z
                englishText += lineEnglish.replaceAll("[^A-Za-z]+", "");
                lineEnglish = engligh.readLine();
            }

            analyzeEnglishText(englishText);



            String lineFrench = french.readLine();
            String frenchText = "";
            while (lineFrench != null)
            {
                lineFrench = lineFrench.toLowerCase();
                // Append the next line and remove everything except letters a-z
                frenchText += lineFrench.replaceAll("[^A-Za-z]+", "");
                lineFrench = french.readLine();
            }


            analyzeFrenchText(frenchText);

            String linePolish = polish.readLine();
            String polishText = "";
            while (linePolish != null)
            {
                linePolish = linePolish.toLowerCase();
                // Append the next line and remove everything except letters a-z
                polishText += linePolish.replaceAll("[^A-Za-z]+", "");
                linePolish = polish.readLine();
            }

            analyzePolishText(polishText);

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
                polish.close();
                french.close();
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
        //finding probabilities english
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                countSequenceEnglishProb[i][j] = countSequenceEnglish[i][j] / (text.length() - 1 + LETTERS * LETTERS);
            }
        }

//        displayProb(countSequenceEnglish);
//        displayProb(countSequenceEnglishProb);
    }

    private void analyzeFrenchText(String text)
    {
        for (int i = 1; i < text.length(); i++)
        {
            countSequenceFrench[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
            //System.out.println(text.charAt(i) + " " + text.charAt(i - 1) + " " + (text.charAt(i) - 97) + " " + (text.charAt(i - 1) - 97));
        }
        //finding probabilities french
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                countSequenceFrenchProb[i][j] = countSequenceFrench[i][j] / (text.length() - 1 + LETTERS * LETTERS);
            }
        }
        
//        displayProb(countSequenceFrench);
//        displayProb(countSequenceFrenchProb);

    }

    private void analyzePolishText(String text)
    {
        for (int i = 1; i < text.length(); i++)
        {
            countSequencePolish[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
            //System.out.println(text.charAt(i) + " " + text.charAt(i - 1) + " " + (text.charAt(i) - 97) + " " + (text.charAt(i - 1) - 97));
        }
        //finding probabilities polish
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                countSequencePolishProb[i][j] = countSequencePolish[i][j] / (text.length() - 1 + LETTERS * LETTERS);
            }
        }

//        displayProb(countSequencePolish);
//        displayProb(countSequencePolishProb);
    }

    private void displayProb(float prob[][])
    {
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                System.out.print(prob[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void determineLanguage(String sentence)
    {
        String soDisplay = sentence;
        sentence = sentence.toLowerCase();
        sentence = sentence.replaceAll("[^A-Za-z]+", "");

        float english = 0f;
        float french = 0f;
        float polish = 0f;

        for (int i = 1; i < sentence.length(); i++)
        {
            english += Math.log10(countSequenceEnglishProb[sentence.charAt(i) - 97][sentence.charAt(i - 1) - 97]);
            french += Math.log10(countSequenceFrenchProb[sentence.charAt(i) - 97][sentence.charAt(i - 1) - 97]);
            polish += Math.log10(countSequencePolishProb[sentence.charAt(i) - 97][sentence.charAt(i - 1) - 97]);
        }
        if (english > french)
        {
            if (english > polish)
            {
                System.out.println(soDisplay + "    <--- English");
            }
            else
            {
                System.out.println(soDisplay + "    <--- Polish");
            }
        }
        else
        {
            if (french > polish)
            {
                System.out.println(soDisplay + "    <--- French");
            }
            else
            {
                System.out.println(soDisplay + "    <--- Polish");
            }
        }
    }
}
