package lab07SymmetricKeyProgramming;

import java.nio.charset.Charset;
import java.security.Security;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SimpleSymmetricExample {
	public static void main(String[] args) throws Exception {

		Security.addProvider(new BouncyCastleProvider());

		String keyString = "12c4U05:)a201i5iya2A";
		String messageString = "And the band begins to play... We all live in a yellow submarine yellow submarine";

		byte[] keyBytes = keyString.getBytes(Charset.forName("UTF-8"));
		System.out.println(keyBytes.length);
		byte[] messageBytes = messageString.getBytes(Charset.forName("UTF-8"));

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec = new PBEKeySpec(keyString.toCharArray(), "123456789".getBytes(), 4096, 128);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

		System.out.println("input text : " + messageString);

		// encryption pass
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ciphertext = cipher.doFinal(messageBytes);
		byte[] iv = cipher.getIV();
		System.out.println("iv :" + Utils.toHex(iv));
		System.out.println("cipher text: " + Utils.toHex(ciphertext) + " bytes: " + ciphertext.length);

		// decryption pass
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		
//		String bla = "a2838036d47ec016d112916f10ff21b2e6da27c68e8a3f64835e08f2f4290fa8";
//		byte[] test = DatatypeConverter.parseHexBinary(bla);
//		
//		byte[] plaintext = cipher.doFinal(test);
		byte[] plaintext = cipher.doFinal(ciphertext);
		
		System.out.println("plain text : " + new String(plaintext) + " bytes: " + plaintext.length);
	}
	
	
	/**
	 * ENCRYPT
	 * Esteban			A3b472xAbc
	 * Julien			x752jU5ie2
	 * Dorette			1234567
	 * Lucy&Charlie		x3y45uCiC451y!?36
	 * Micha&Felix		password
	 * Solveig			drowssap
	 * Ruslan&Stiliyana 12c4U05:)a201i5iya2A
	 */
}