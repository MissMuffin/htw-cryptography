package lab10DictionaryAttack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Dictionary {

	private static String wordsPath = "src/lab10DictionaryAttack/moarWords.txt";
	private static String commonPath = "src/lab10DictionaryAttack/common_words_en.txt";
	private ArrayList<String> words; 
	private ArrayList<String> common;

	public Dictionary() throws Exception {		
		words = new ArrayList<>();
		common = new ArrayList<>();
		
		FileReader fr = new FileReader(new File(wordsPath));
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		while((line = br.readLine())!=null) {
			String word = line;
			words.add(word);
		}
		br.close();
		
		FileReader f = new FileReader(new File(commonPath));
		BufferedReader b = new BufferedReader(f);
		
		String lines;
		while((lines = b.readLine())!=null) {
			String word = lines;
			common.add(word);
		}
		b.close();
	}
	
	public ArrayList<String> getDictionary() {
		return words;
	}
		
	public int countValidWords(String message) throws Exception {		
		int count = 0;
		String[] messageWords = message.split("\\s+");//optimization: dont use split, iterate over string 
		int end = messageWords.length < 6 ? messageWords.length : 6;
		for(int i = 0; i < end; i++){//String s : messageWords) {			
			for (String word : common) {
				if (messageWords[i].toLowerCase().equals(word) 
						|| messageWords[i].toUpperCase().equals(word) 
						|| (capitalize(messageWords[i])).equals(word)) {
					count++;
				}
			}
//			if(count==0) break;
		}		
		return count;
	}
	
	public String capitalize(String s) {
		String output = "";
		try {
			output = s.substring(0, 1).toUpperCase() + s.substring(1);
		} catch (Exception e) {
			return output;
		}
		return output;
	}
}
