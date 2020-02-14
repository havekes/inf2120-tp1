package app.exceptions;

public class InvalidRNAStringLengthException extends RuntimeException {
    
    @Override
    public String toString() {
        return "La chaine d'ARN doit etre d'une longueur divisible par 3";
    }
}
