package com.cox.bis.customer.preference.util;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SimpleCrypto {

	public SimpleCrypto() {
		// TODO Auto-generated constructor stub
	}
	/***
	 * Utility Method: Decrypts ICOMS password to plain text 
	 * @param encryptedText
	 * @param encryptedText
	 * @return
	 */
	public static String decrypt(String encryptedText){
		byte[] plainText = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] hash = md.digest("SimpleCrypto".getBytes("UTF-8"));
			final byte[] keyBytes = Arrays.copyOf(hash, 24);
		   	for (int j = 0, k = 16; j < 8;) {
		   		keyBytes[k++] = keyBytes[j++];
		   	}
		   	
		   	byte[] decodedCipherText = Base64.decodeBase64(encryptedText.getBytes());
			DESedeKeySpec dks;
			
			dks = new DESedeKeySpec(keyBytes);
			
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			SecretKey desKey = skf.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			
			cipher.init(Cipher.DECRYPT_MODE, desKey);
		   	
	    	plainText = cipher.doFinal(decodedCipherText);
	    	return new String(plainText, "UTF-8");	
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| UnsupportedEncodingException | InvalidKeySpecException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			try {
				Writer sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
			} catch(Exception ex) {
			}
		}
		return null;
	}
	
	/***
	 * Utility Method: Encrypts ICOMS password to plain text 
	 * @param rawText
	 * @param context
	 * @return
	 */
	public static String encrypt(String rawText){
		byte[] cipherText = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] hash = md.digest("SimpleCrypto".getBytes("UTF-8"));
			final byte[] keyBytes = Arrays.copyOf(hash, 24);
		   	for (int j = 0, k = 16; j < 8;) {
		   		keyBytes[k++] = keyBytes[j++];
		   	}
			DESedeKeySpec dks;
			
			dks = new DESedeKeySpec(keyBytes);
			
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			SecretKey desKey = skf.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
		   	
	    	cipherText = cipher.doFinal(rawText.getBytes());
		   	
		   	byte[] encodedCipherText = Base64.encodeBase64(cipherText);
	    	return new String(encodedCipherText, "UTF-8");	
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| UnsupportedEncodingException | InvalidKeySpecException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			try {
				Writer sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
			} catch(Exception ex) {
			}
		}
		return null;
	}
}