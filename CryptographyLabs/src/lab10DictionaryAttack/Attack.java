package lab10DictionaryAttack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.bouncycastle.crypto.MaxBytesExceededException;

public class Attack {

	
	private static String cipherString = "8f67116a3a4ea23a82bf94ca97f12ca59ef0065aa9b07417996fe07f4592e012e9f69fd65b0555fed1bc3ee7ecf7482e7fe6f8a247c76ed56ff160a7e08c7c7bc8d15f4dca48f1f02c029908c35e5c44c711e910d40cfe9b793e4590728db2ea99659fcaaddbcc6b81e6689244526927004b328bc012563939c8219f52e1d1fd";
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
