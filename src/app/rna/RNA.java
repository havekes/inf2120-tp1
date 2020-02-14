package app.rna;

import java.util.ArrayList;
import java.util.List;

import app.exceptions.InvalidRNAStringCharacterException;
import app.exceptions.InvalidRNAStringLengthException;

/**
 * Stocke les acides amines d'une chaine d'ARN
 */
public class RNA {

    private List<AminoAcid> acids;

    /**
     * Cree une chaine d'ARN vide
     */
    public RNA() {
        acids = new ArrayList<>(0);
    }

    /**
     * Interprete la <code>String</code> en entree pour creer les acide amines d'une chaine
     *      * 
     * @param rawRNAString Chaine de caracteres representant l'ARN
     * @throws InvalidRNAStringLengthException Lorsque la longueur de la chaine n'est pas un multiple de 3
     * @throws InvalidRNAStringCharacterException Lorsque la chaine contient des caracteres autres que A, C, G, U
     */
    public RNA(String rawRNAString)
    throws InvalidRNAStringLengthException, InvalidRNAStringCharacterException {
        parseRawRNAString(rawRNAString);
    }

    /**
     * @return Nombre d'acides amines dans la chaine
     */
    public int size() {
        return acids.size();
    }

    /**
     * Calcule la similarite avec une autre chaine
     * @param otherRNA Autre chaine d'ARN
     * @param maxDeviation Distance maximale (d)
     * @return Un nombre entre 0 et 1 representant la similarite entre les deux chaines
     */
    public double compare(RNA otherRNA, int maxDeviation) {
        // Ajouter la deviation de s (this) par rapport a t (otherRNA)
        // er la deviation de t par rapport a s
        var totalDeviation = RNA.computeDeviation(this, otherRNA, maxDeviation)
            + RNA.computeDeviation(otherRNA, this, maxDeviation);
        
        // Faire la moyenne ponderee
        var averageDeviation = (double) totalDeviation / ((size() + otherRNA.size()) * maxDeviation);

        // Petit calcul
        return Math.exp(-6 * Math.pow(averageDeviation, 2));
    }

    public String toString() {
        final var sb = new StringBuilder();

        for (AminoAcid acid: acids) {
            sb.append(acid);
        }

        return sb.toString();
    }

    /**
     * Calcule la deviation totale de la deuxieme chaine par rapport a la premiere
     * 
     * @param rna1 Premiere chaine
     * @param rna2 Deuxieme chaine
     * @param maxDeviation Distance maximale (d)
     * @return Deviation totale
     */
    private static int computeDeviation(RNA rna1, RNA rna2, int maxDeviation) {
        final var acids1 = rna1.acids;
        final var acids2 = rna2.acids;
        int totalDeviation = 0;

        for (var i = 0; i < acids1.size(); i++) {
            Integer deviation = null;
            for (var j = 0; j < acids2.size(); j++) {
                if (acids1.get(i).equals(acids2.get(j))) {
                    // Si les acides sont les memes
                    // et que la deviation est plus petite que la derniere deviation enregistree
                    // alors on enregistre la deviation
                    if (deviation == null || Math.abs(i - j) < deviation) {
                        deviation = Math.abs(i - j);
                    }
                }
            }
            // On verifie que la deviation ne depasse pas la distance maximale
            if (deviation != null) {
                totalDeviation += Math.min(deviation, maxDeviation);
            } else {
                totalDeviation += maxDeviation;
            }
        }

        return totalDeviation;
    }

    /**
     * Interprete une chaine de charactere en acide amines
     * 
     * @param rawRNAString Chaine de caracteres representant l'ARN
     * @throws InvalidRNAStringLengthException Lorsque la longueur de la chaine n'est pas un multiple de 3
     * @throws InvalidRNAStringCharacterException Lorsque la chaine contient des caracteres autres que A, C, G, U
     */
    private void parseRawRNAString(String rawRNAString)
    throws InvalidRNAStringLengthException, InvalidRNAStringCharacterException {
        // Verifier que la chaine est de la bonne longueur
        if (rawRNAString.length() % 3 != 0) {
            throw new InvalidRNAStringLengthException();
        }
        acids = new ArrayList<>(rawRNAString.length() / 3);

        // Diviser la chaine en codons et les interpreter
        for (var i = 0; i < rawRNAString.length(); i += 3) {
            final var codon = rawRNAString.substring(i, i + 3);
            try {
                acids.add(new AminoAcid(codon));
            } catch (InvalidRNAStringCharacterException e) {
                e.setPosition(e.getPosition() + i + 1);
                throw e;
            }
        }
    }
}
