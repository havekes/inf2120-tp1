package app.exceptions;

public class InvalidRNAStringCharacterException extends RuntimeException {
    
    private char invalidChar;
    private int position;

    public InvalidRNAStringCharacterException(char invalidChar, int position) {
        this.invalidChar = invalidChar;
        this.position = position;
    }
        
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("Le carractere %c a la position %d n'est pas un carractere d'ARN valide.", 
            invalidChar, position);
    }
}
