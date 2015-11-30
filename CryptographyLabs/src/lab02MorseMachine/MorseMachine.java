package lab02MorseMachine; 

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class MorseMachine {

	
	static HashMap<Character, String> alphabet;
	
	public MorseMachine(){
		alphabet = new HashMap<Character, String>();
		//alphabet
		alphabet.put('A', ".-");
		alphabet.put('B', "-...");
		alphabet.put('C', "-.-.");
		alphabet.put('D', "-..");
		alphabet.put('E', ".");
		alphabet.put('F', "..-.");
		alphabet.put('G', "--.");
		alphabet.put('H', "....");
		alphabet.put('I', "..");
		alphabet.put('J', ".---");
		alphabet.put('K', "-.-");
		alphabet.put('L', ".-..");
		alphabet.put('M', "--");
		alphabet.put('N', "-.");
		alphabet.put('O', "---");
		alphabet.put('P', ".--.");
		alphabet.put('Q', "--.-");
		alphabet.put('R', ".-.");
		alphabet.put('S', "...");
		alphabet.put('T', "-");
		alphabet.put('U', "..-");
		alphabet.put('V', "...-");
		alphabet.put('W', ".--");
		alphabet.put('X', "-..-");
		alphabet.put('Y', "-.--");
		alphabet.put('Z', "--..");
		//numbers
		alphabet.put('0', "-----");
		alphabet.put('1', ".----");
		alphabet.put('2', "..---");
		alphabet.put('3', "...--");
		alphabet.put('4', "....-");
		alphabet.put('5', ".....");
		alphabet.put('6', "-....");
		alphabet.put('7', "--...");
		alphabet.put('8', "---..");
		alphabet.put('9', "----.");
		//special characters
		alphabet.put('.',".-.-.-");
		alphabet.put(',',"--..--");
		alphabet.put('?',"..--..");
		alphabet.put('\'',".----.");
		alphabet.put('!',"-.-.--");
		alphabet.put('/',"-..-.");
		alphabet.put('(',"-.--.");
		alphabet.put(')',"-.--.-");
		alphabet.put('&',".-...");
		alphabet.put(':',"---...");
		alphabet.put(';',"-.-.-.");
		alphabet.put('=',"-...-");
		alphabet.put('+',".-.-.");
		alphabet.put('-',"-....-");
		alphabet.put('_',"..--.-");
		alphabet.put('"',".-..-.");
		alphabet.put('$',"...-..-");
		alphabet.put('@',".--.-.");
		//Ä, Ö, Ü
		alphabet.put('Ä', ".-.-");
		alphabet.put('Ö', "---.");
		alphabet.put('Ü', "..--");
	}
	
	private static String encode (String plainText) {
		
		String morseCode = "";
		plainText = plainText.toUpperCase(Locale.ENGLISH);
		
		for (char c : plainText.toCharArray()) {
			if (c == ' ') {
				morseCode += "   ";
			}
			else{
				morseCode += alphabet.get(c) + " ";
			}			
		}		
		return morseCode;		
	}
	
	private static String decode (String cipher) {

		String plainText = "";
		
		//split into words
		String[] words = cipher.trim().split("(\\s[^.-]*\\s)");
		
		//split into letters
		for (String word : words) {
			String[] letters = word.trim().split("\\s");
			
			//decode letter and append 
			for (String letter : letters) {
				plainText += decodeMorseLetter(letter);
			}
			
			//append space after word
			plainText += " ";
		}	
		return plainText;
	}
	
	private static String decodeMorseLetter(String letter) {
		for (Map.Entry<Character, String> morseLetter : alphabet.entrySet()) {
			if (morseLetter.getValue().equals(letter)) {
				return morseLetter.getKey().toString();
			}
		}		
		return null;
	}
	
	private static void consoleInput(){
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("Type enc for encoding to morse, dec for decoding from morse, exit for quitting the program");
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("enc")) {
				
				System.out.println("Enter the text you want to encode");
				input = scanner.nextLine();
				System.out.println(encode(input));
				
			} else if(input.equalsIgnoreCase("dec")) {
				
				System.out.println("Enter the text you want to decode");
				input = scanner.nextLine();
				System.out.println(decode(input));
				
			} else if(input.equalsIgnoreCase("exit")) {
				break;
			}
			else{
				System.out.println("We could not identify your request. Please try again.");
			}
		}
		scanner.close();
	}
	
	
	public static void main(String[] args){
		MorseMachine mm = new MorseMachine();
		consoleInput();
	}	
}