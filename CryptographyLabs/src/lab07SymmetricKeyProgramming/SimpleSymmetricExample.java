package lab07SymmetricKeyProgramming;

import java.nio.charset.Charset;
import java.security.Security;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SimpleSymmetricExample {
	public static void main(String[] args) throws Exception {

		Security.addProvider(new BouncyCastleProvider());

		String keyString = "B7c332yXza";
		String messageString = "hallowelt";

		byte[] keyBytes = keyString.getBytes(Charset.forName("UTF-8"));
		System.out.println(keyBytes.length);
		byte[] messageBytes = messageString.getBytes(Charset.forName("UTF-8"));

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1", "BC");
		KeySpec spec = new PBEKeySpec(keyString.toCharArray(), "123456789".getBytes(), 4096, 128);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");

		System.out.println("input text : " + messageString);

		// encryption pass
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ciphertext = cipher.doFinal(messageBytes);
		// byte[] iv = cipher.getIV();
		System.out.println("cipher text: " + Utils.toHex(ciphertext) + " bytes: " + ciphertext.length);

		// decryption pass
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plaintext = cipher.doFinal(ciphertext);// removed offset of
														// ctlength

		System.out.println("plain text : " + Utils.toHex(plaintext) + " bytes: " + plaintext.length);
		System.out.println(new String(plaintext));
	}
}