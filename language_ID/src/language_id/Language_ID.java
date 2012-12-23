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

        /**
         * Good classification
         */
        p.determineLanguage("Where are Jim and Julia?");
        p.determineLanguage("Ils reviendront au final, peut-etre.");
//        p.determineLanguage("Hello, my name is Greg");
//        p.determineLanguage("Bonjour, je m'appalle Grzegorz");
//        p.determineLanguage("Dziendobry, nazywam sie Grzegorz");
//
//        /**
//         * Wrong classification
//         */
//        System.out.println("--------------------------");
//        p.determineLanguage("Hello, my name is Grzegorz");
//        p.determineLanguage("Grzegorz went to see a zebra at the zoo");
//        p.determineLanguage("Novembre a ete chaud");
//        p.determineLanguage("Your country house in Joliette");
//        p.determineLanguage("I am cold");
//        System.out.println("--------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence:\n");
        String sentence = scanner.nextLine();
        while (!sentence.isEmpty())
        {
            p.determineLanguage(sentence);
            sentence = scanner.nextLine();
        }

    }
}
