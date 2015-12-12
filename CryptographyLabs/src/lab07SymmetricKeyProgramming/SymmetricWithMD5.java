package lab07SymmetricKeyProgramming;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class SymmetricWithMD5 {
	public static void main(String[] args) throws Exception {
		
		Security.addProvider(new BouncyCastleProvider());
		
		String messageString = "12345678";
		byte[] message = messageString.getBytes(Charset.forName("UTF-8"));
		byte[] paddedMessage = new byte[message.length + 8 - (message.length % 8)];
		System.arraycopy(message, 0, paddedMessage, 0, message.length);
		
		String keyString = "DODODELWoD3LBOOGieMoGY";			
		byte[] keyBytes = keyString.getBytes(Charset.forName("UTF-8"));

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(keyBytes, 0, keyBytes.length);
		byte[] hashedKey = md.digest();
		
		SecretKeySpec key = new SecretKeySpec(hashedKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
		System.out.println(new String(message));
		System.out.println("input text : " + Utils.toHex(paddedMessage));

		// encryption pass

		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(paddedMessage);		
		System.out.println("cipher text: " + Utils.toHex(cipherText) + " bytes: " + cipherText.length);

		// decryption pass

		cipher.init(Cipher.DECRYPT_MODE, key);
//		byte[] plainText = cipher.doFinal(cipherText);
		
		byte[] plainText = cipher.doFinal(DatatypeConverter.parseHexBinary("20070030ad5f170a973b2e60f81185282423182c1d2db1d382eff02dea0f8488"));
		System.out.println("plain text : " + Utils.toHex(plainText) + " bytes: " + plainText.length);
		System.out.println(new String(plainText));
	}
	
	/**
	 * Solvejg, Relana, Fabian
	 * key: DODODELWoD3LBOOGieMoGY
	 * plain text : 57686174206469642074686520666f782074656c6c20796f7520616761696e3f bytes: 32
	 * What did the fox tell you again?
	 */
}
