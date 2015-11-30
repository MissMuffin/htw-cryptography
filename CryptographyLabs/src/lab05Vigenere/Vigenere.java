package lab05Vigenere;

public class Vigenere {
    
    String key = "";
    String plaintext = "";
    String cipher = "";
    
    public Vigenere(String keyword, String text, boolean isPlaintext){
        this.key = keyword.toUpperCase();
        if (isPlaintext)
            this.plaintext = text.toUpperCase();
        else
            this.cipher = text.toUpperCase();
    }
    
    
    public String encode(){
        char newChar;
        cipher = "";
        int spaceCounter = 1;
        for(int i= 0; i < plaintext.length(); i++){
            
            newChar = (char) (plaintext.charAt(i) + key.charAt(i % key.length()) - 65);
            
            if (newChar > 90)
                newChar = (char) (65 + newChar-91);
            
            cipher += newChar;
            if (spaceCounter%5 == 0)
                cipher += " "; 
            spaceCounter++;
            
        }
        
        return cipher;
    }
    
    public String decode(){
        char newChar;
        plaintext = "";
        cipher = cipher.replaceAll("\\s+","");
        for(int i= 0; i < cipher.length(); i++){
            newChar = (char) (cipher.charAt(i) - key.charAt(i % key.length()) + 65);
                
            if (newChar < 65)
                newChar = (char) (90 - (64 -newChar));
            
            plaintext += newChar;
        }
        
        return plaintext;
    }
    

    public static void main(String[] args) {
        Vigenere test = new Vigenere("Blume", "HalloHiertestenwirmaldenvigenerecode", true);
        
        System.out.println(test.encode());
        
        System.out.println(test.decode());

    }

}

