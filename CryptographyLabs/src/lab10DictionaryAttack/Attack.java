package lab10DictionaryAttack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.bouncycastle.crypto.MaxBytesExceededException;

public class Attack {

	
	private static String cipherString = "e5a11c718e6f3fa60de2697a3e4fd470717e3ab1b5d9bac9afb36a826c6c63ae3e253169018275eea0929b714390e8b843feb85ad217ac7b3f122b6cfd079d7f8cdd69fe94564cee325d1d72538879ad951cb1dfee999506099b86753270f176cb35e8f7f2c1a345";
	private static GenSymmetricCrypto symm;
	
	
	public static void main(String[] args) throws Exception{
		int maxWordCount = 0;
		symm = new GenSymmetricCrypto();
		String key = "";
		String decMessage = "";
		Dictionary dict = new Dictionary();
		ArrayList<String> wordsEn = dict.getDictionary();
		
		System.out.println("Cracking...");
		
		int test = 0;
		for(String word : wordsEn) {
			word = word.toLowerCase();
			String plaintext = symm.decrypt(cipherString, word);
			
			int  found = dict.countValidWords(plaintext);
			if (maxWordCount < found) {
				System.out.println(found);
				System.out.println(word);
				System.out.println(plaintext);
				maxWordCount = found;
				key = word;
				decMessage = plaintext;
			}
			if (test%1000 == 0) System.out.println(test);
			test++;
		}
		
//						test = 0;
//		for(String word : wordsEn) {
//			word = dict.capitalize(word);
//			String plaintext = symm.decrypt(cipherString, word);
//			
//			int  found = dict.countValidWords(plaintext);
//			if (maxWordCount < found) {
//				System.out.println(found);
//				maxWordCount = found;
//				key = word;
//				decMessage = plaintext;
//			}
//			if (test%1000 == 0) System.out.println(test);
//			test++;
//		}
		
		System.out.println("DONE");
		System.out.println("Probable key: " + key);
		System.out.println("Message: " + decMessage);
	}	
	
	/*
	 * Ruslan, Stilyana
	 * Probable key: zeppelin
	 * Message: Arguing with an Engineer is a lot like wrestling in the mud with a pig, after a couple of hours you realize the pig likes it.  
	 * 
	 * Robert, Esteban
	 * Probable key: dictionary
	 * Message: the sheep ate the dog then went for a swim in the river  
	 */
}
