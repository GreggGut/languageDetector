/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package language_id;

import java.util.Scanner;

/**
 *
 * @author Greg
 */
public class Language_ID
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Probabilities p = new Probabilities();

        p.determineLanguage("Hello, my name is Greg");
        p.determineLanguage("Hello, my name is Grzegorz");
        p.determineLanguage("Bonjour, je m'appalle Grzegorz");
        p.determineLanguage("Dziendobry, nazywam sie Grzegorz");
        p.determineLanguage("Where are Jim and Julia?");
        p.determineLanguage("Ils reviendront au final, peut-etre.");
        p.determineLanguage("Grzegorz went to see the zebra at the zoo");
        p.determineLanguage("pojechalem do afryki");
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence:\n");
        String sentence = scanner.nextLine();
        while(!sentence.isEmpty())
        {
            p.determineLanguage(sentence);
            sentence = scanner.nextLine();
        }
        
    }
}
