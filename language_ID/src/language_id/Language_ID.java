/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package language_id;

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
        p.readTexts();

        p.determineLanguage("Hello my name is Greg");
        p.determineLanguage("Bonjour je m'appalle Greg");
        p.determineLanguage("Dziendobry nazywam sie Greg");

    }
}
