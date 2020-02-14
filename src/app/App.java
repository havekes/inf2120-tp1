package app;

import java.util.Scanner;
import app.exceptions.InvalidRNAStringCharacterException;
import app.exceptions.InvalidRNAStringLengthException;
import app.rna.RNA;

/**
 * Classe principale, demande les informations et affiche les resultats
 */
public class App {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Methode d'entree, demande les informations et affiche les resultats
     */
    public static void main(String[] args) {

        System.out.print("Premiere chaine d'ARN : ");
        final var rna1 = App.readRNAString();
        System.out.print("Deuxieme chaine d'ARN : ");
        final var rna2 = App.readRNAString();
        System.out.print("Deviation maximale : ");
        final var deviation = App.readDeviation();

        System.out.println(rna1.compare(rna2, deviation));
    }

    /**
     * Lit l'entree et tente d'interpreter la chaine d'ARN
     * Si l'entre est i" beforenvalide, quite le programme
     * 
     * @return objet <code>RNA</code> correspondant
     */
    public static RNA readRNAString() {
        var retry = true;
        var rna = new RNA();

        do {
            final var input = sc.next();

            try {
                rna = new RNA(input);
                retry = false;
            } catch (InvalidRNAStringLengthException e) {
                System.out.println(e);
                System.exit(-1);
            } catch (InvalidRNAStringCharacterException e) {
                System.out.println(e);
                System.exit(-1);
            }
        } while (retry);

        return rna;
    }

    /**
     * Lit l'entree au clavier et tente d'interpreter la valeur
     * Si la valeur est inferieure a 1, quite le programme
     * 
     * @return valeur de deviation
     */
    public static int readDeviation() {
        int deviation;

        do {
            deviation = sc.nextInt();
            if (deviation <= 0) {
                System.out.println("La deviation doit etre superieure a 0");
                System.exit(-1);
            }
        } while (deviation <= 0);

        return deviation;
    }
}
