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
		
//		String messageString = "In the town where I was born Lived a man who sailed to sea";
//		String messageString = "And he told us of his life In the land of submarines";
//		String messageString = "So we sailed on to the sun Till we found a sea of green";
//		String messageString = "And we lived beneath the waves In our yellow submarine";
//		String messageString = "We all live in a yellow submarine Yellow submarine yellow submarine";
//		String messageString = "And our friends are all aboard Many more of them live next door";
		String messageString = "And the band begins to play... We all live in a yellow submarine yellow submarine";
		byte[] message = messageString.getBytes(Charset.forName("UTF-8"));
		byte[] paddedMessage = new byte[message.length];
		System.arraycopy(message, 0, paddedMessage, 0, message.length);
		
		String keyString = "12c4U05:)a201i5iya2A";			
		byte[] keyBytes = keyString.getBytes(Charset.forName("UTF-8"));

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(keyBytes, 0, keyBytes.length);
		byte[] hashedKey = md.digest();
		
		SecretKeySpec key = new SecretKeySpec(hashedKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
		System.out.println(new String(message));
		System.out.println("input text : " + Utils.toHex(paddedMessage) + " bytes: " + paddedMessage.length);

		// encryption pass
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(paddedMessage);		
		System.out.println("cipher text: " + Utils.toHex(cipherText) + " bytes: " + cipherText.length);

		// decryption pass
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = cipher.doFinal(cipherText);
		
//		System.out.println(Utils.toHex(DatatypeConverter.parseHexBinary("440619ee3a8c29d64380f26a21c819bdf66b0c4d3d0a79a2530d19eda5c4bf4281f11c2f7774074e52a6e6e332dab62e45c51c2b62e445fb2dd3880e98d39eea3f19def09013eabef09c772759ef431720c06f20f295034f3fc81b749765ed7d1aca997ed753c84e6a0564fedf900f21")).length());
//		byte[] plainText = cipher.doFinal(DatatypeConverter.parseHexBinary("440619ee3a8c29d64380f26a21c819bdf66b0c4d3d0a79a2530d19eda5c4bf4281f11c2f7774074e52a6e6e332dab62e45c51c2b62e445fb2dd3880e98d39eea3f19def09013eabef09c772759ef431720c06f20f295034f3fc81b749765ed7d1aca997ed753c84e6a0564fedf900f21"));
		System.out.println("plain text : " + Utils.toHex(plainText) + " bytes: " + plainText.length);
		System.out.println(new String(plainText));
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
	
	/**
	 * Solvejg, Relana, Fabian
	 * key: DODODELWoD3LBOOGieMoGY
	 * plain text : 57686174206469642074686520666f782074656c6c20796f7520616761696e3f bytes: 32
	 * What did the fox tell you again?
	 */
	
	/**
	 * group: felix, micha
	 * key: messiahpassengerflinchzero
	 * plain text : 5768656e204a657375732720706c616e652073756464656e6c7920776173206869742062792074757262756c656e6365732c206865206469646e277420666c696e63682e2057687920776f756c642068652c206865277320746865206d65737369616821 bytes: 100
	 * When Jesus' plane suddenly was hit by turbulences, he didn't flinch. Why would he, he's the messiah!
	 */
}
