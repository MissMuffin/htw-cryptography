package lab10DictionaryAttack;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class GenSymmetricCrypto {
	
	private Cipher cipherObject;
	
	public GenSymmetricCrypto() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		cipherObject = Cipher.getInstance("Blowfish/ECB/PKCS5Padding", "BC");
	}
	
	public byte[] encrypt(String messageString, String keyString) throws Exception {		
		byte[] keyBytes = keyString.getBytes(Charset.forName("UTF-8"));
		byte[] messageBytes = messageString.getBytes(Charset.forName("UTF-8"));
		        
    	cipherObject.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(
    			MessageDigest.getInstance("MD5").digest(keyBytes), 
    			"Blowfish"));
        byte[] ciphertext = cipherObject.doFinal(messageBytes);
        
//        System.out.println("cipher text: " + Utils.toHex(ciphertext) + " bytes: " + ciphertext.length);
        return ciphertext;
	}
	
	public String decrypt(String cipherString, String keyString) throws Exception {		
		byte[] keyBytes = keyString.getBytes(Charset.forName("UTF-8"));
		byte[] cipherBytes = DatatypeConverter.parseHexBinary(cipherString);
		                
    	cipherObject.init(Cipher.DECRYPT_MODE, new SecretKeySpec(
    			MessageDigest.getInstance("MD5").digest(keyBytes),
    			"Blowfish"));
        byte[] plaintext = cipherObject.doFinal(cipherBytes);
 
//        System.out.println("plain text: " + new String(plaintext) + " bytes: " + plaintext.length);
        return new String(plaintext);
	}
	
//	 public static void main(String[] args) throws Exception {
//        String key = "hypnosis";
//        String message = "The sloop rocked. Jack had it finally headed into the wind. He looked around for Mary. She had jumped out and was hurrying back, scrambling across the rocks toward the dark, struggling figure that even as he watched was once more engulfed in the surf. Letting go the lines, Jack sprang toward the stern of the sloop. But just then another giant blow came, struck the sail like a great fist of air, and sent the boom slashing at the back of his head. His last recollection was being toppled out onto the rocks and wondering how he could cling to them while unconscious.";
//        
//        GenSymmetricCrypto gen = new GenSymmetricCrypto();
//        
//        byte[] cipher = gen.encrypt(message, key);
//        gen.decrypt(Utils.toHex(cipher), key);        
//	 }
}
