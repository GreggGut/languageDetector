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

    private final int LETTERS = 26;
    private final float SMOOTH = 0.5f;
    private float countSequenceEnglish[][] = new float[LETTERS][LETTERS];
    private float countSequenceFrench[][] = new float[LETTERS][LETTERS];
    private float countSequencePolish[][] = new float[LETTERS][LETTERS];
    private float countSequenceEnglishProb[][] = new float[LETTERS][LETTERS];
    private float countSequenceFrenchProb[][] = new float[LETTERS][LETTERS];
    private float countSequencePolishProb[][] = new float[LETTERS][LETTERS];

    Probabilities()
    {
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
            {
                countSequenceEnglish[i][j] = SMOOTH;
                countSequenceFrench[i][j] = SMOOTH;
                countSequencePolish[i][j] = SMOOTH;

                countSequenceEnglishProb[i][j] = 0f;
                countSequenceFrenchProb[i][j] = 0f;
                countSequencePolishProb[i][j] = 0f;
            }
        }

        readTexts();
    }

    private void readTexts()
    {
        BufferedReader english = null;
        BufferedReader french = null;
        BufferedReader polish = null;
        try
        {

            english = new BufferedReader(new FileReader("english.txt"));
            french = new BufferedReader(new FileReader("french.txt"));
            polish = new BufferedReader(new FileReader("polish.txt"));

            String lineEnglish = english.readLine();
            String englishText = "";
            while (lineEnglish != null)
            {
                lineEnglish = lineEnglish.toLowerCase();
                // Append the next line and remove everything except letters a-z
                englishText += lineEnglish.replaceAll("[^A-Za-z]+", "");
                lineEnglish = english.readLine();
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
            //Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot open " + ex.getLocalizedMessage()+ ", exiting...");
        }
        catch (IOException ex)
        {
            Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                if (english != null)
                {
                    english.close();
                }
                if (polish != null)
                {
                    polish.close();
                }
                if (french != null)
                {
                    french.close();
                }
                if (english == null || french == null || polish == null)
                {
                    System.exit(0);
                }
            }
            catch (IOException ex)
            {
                //Logger.getLogger(Probabilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void analyzeEnglishText(String text)
    {
        //Count the sequence of all consequtive characters for the english text
        for (int i = 1; i < text.length(); i++)
        {
            countSequenceEnglish[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
        }
        //finding probabilities english
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                countSequenceEnglishProb[i][j] = countSequenceEnglish[i][j] / (text.length() - 1 + SMOOTH * LETTERS * LETTERS);
            }
        }

//        displayProb(countSequenceEnglish);
//        displayProb(countSequenceEnglishProb);
    }

    private void analyzeFrenchText(String text)
    {
        //Count the sequence of all consequtive characters for the french text
        for (int i = 1; i < text.length(); i++)
        {
            countSequenceFrench[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
        }
        //finding probabilities french
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                countSequenceFrenchProb[i][j] = countSequenceFrench[i][j] / (text.length() - 1 + SMOOTH * LETTERS * LETTERS);
            }
        }

//        displayProb(countSequenceFrench);
//        displayProb(countSequenceFrenchProb);

    }

    private void analyzePolishText(String text)
    {
        //Count the sequence of all consequtive characters for the polish text
        for (int i = 1; i < text.length(); i++)
        {
            countSequencePolish[text.charAt(i) - 97][text.charAt(i - 1) - 97]++;
        }
        //finding probabilities polish
        for (int i = 0; i < LETTERS; i++)
        {
            for (int j = 0; j < LETTERS; j++)
            {
                countSequencePolishProb[i][j] = countSequencePolish[i][j] / (text.length() - 1 + SMOOTH * LETTERS * LETTERS);
            }
        }

//        displayProb(countSequencePolish);
//        displayProb(countSequencePolishProb);
    }

    /**
     * Test function that will display any 2D array
     *
     * @param prob A 2D array
     */
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

    /**
     * This function determines what language a given sentence is
     *
     * @param sentence The sentence to be analyzed
     */
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

        //System.out.println("English: " + english + " French: " + french + " Polish: " + polish);

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
