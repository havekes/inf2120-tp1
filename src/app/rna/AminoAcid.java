package app.rna;

import java.util.List;

import app.exceptions.InvalidRNAStringCharacterException;

/**
 * Represente un acide amine
 */
public class AminoAcid {

    static final List<Character> codonChars = List.of('A', 'C', 'G', 'U');

    String acid;

    /**
     * Cree un nouvel acide amine a partir d'un codon
     * @param codon Un codon (une chaine de 3 caracteres)
     */
    public AminoAcid(String codon) {
        parseCodon(codon);
    }

    public boolean equals(AminoAcid otherAcid) {
        return acid.equals(otherAcid.acid);
    }

    public String toString() {
        return acid;
    }

    /**
     * Interprete un codon en acide amine
     * 
     * @param codon Un codon (une chaine de 3 caracteres)
     * @throws InvalidRNAStringCharacterException Lorsque le codon contient des caracteres autres que A, C, G, U
     */
    private void parseCodon(String codon) throws InvalidRNAStringCharacterException {
        // Verifier que le codon ne contient pas de charactere invalide
        for (var i = 0; i < codon.length(); i++) {
            if (!codonChars.contains(codon.charAt(i))) {
                throw new InvalidRNAStringCharacterException(codon.charAt(i), i);
            }
        }

        // Choisir l'acide amine selon le codon
        switch (codon) {
            case "GCU":
            case "GCC":
            case "GCA":
            case "GCG":
                acid = "Ala";
                break;
            case "CGU":
            case "CGC":
            case "CGA":
            case "CGG":
            case "AGA":
            case "AGG":
                acid = "Arg";
                break;
            case "AAU":
            case "AAC":
                acid = "Asn";
                break;
            case "GAU":
            case "GAC":
                acid = "Asp";
                break;
            case "UGU":
            case "UGC":
                acid = "Cys";
                break;
            case "GAA":
            case "GAG":
                acid = "Glu";
                break;
            case "CAA":
            case "CAG":
                acid = "Gln";
                break;
            case "GGU":
            case "GGC":
            case "GGA":
            case "GGG":
                acid = "Gly";
                break;
            case "CAU":
            case "CAC":
                acid = "His";
                break;
            case "AUU":
            case "AUC":
            case "AUA":
                acid = "Ile";
                break;
            case "UUA":
            case "UUG":
            case "CUU":
            case "CUC":
            case "CUA":
            case "CUG":
                acid = "Leu";
                break;
            case "AAA":
            case "AAG":
                acid = "Lys";
                break;
            case "AUG":
                acid = "Met";
                break;
            case "UUU":
            case "UUC":
                acid = "Phe";
                break;
            case "CCU":
            case "CCC":
            case "CCA":
            case "CCG":
                acid = "Pro";
                break;
            case "UAG":
                acid = "Pyl";
                break;
            case "UGA":
                acid = "Sec";
                break;
            case "UCU":
            case "UCC":
            case "UCA":
            case "UCG":
            case "AGU":
            case "AGC":
                acid = "Ser";
                break;
            case "ACU":
            case "ACC":
            case "ACA":
            case "ACG":
                acid = "Thr";
                break;
            case "UGG":
                acid = "Trp";
                break;
            case "UAU":
            case "UAC":
                acid = "Tyr";
                break;
            case "GUU":
            case "GUC":
            case "GUA":
            case "GUG":
                acid = "Val";
                break;
            case "UAA":
                acid = "Fin";
                break;
        }
    }
}
