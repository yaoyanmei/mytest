package mie_test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class Encryp {

	// KeyGenerator �ṩ�Գ���Կ�������Ĺ��ܣ�֧�ָ����㷨
	private KeyGenerator keygen;
	// SecretKey ���𱣴�Գ���Կ
	private SecretKey deskey;
	
	// Cipher������ɼ��ܻ���ܹ���
	private Cipher c;
	// ���ֽ����鸺�𱣴���ܵĽ��
	private byte[] cipherByte;

	public Encryp(String baseStr) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		deskey = new SecretKeySpec(new Base64Encoder().decode(baseStr), "AES");
		// ����Cipher����,ָ����֧�ֵ�DES�㷨
		c = Cipher.getInstance("AES");
	}
	
	
	/**
	 * ���ַ�������
	 * 
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String Encrytor(String str) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// ������Կ����Cipher������г�ʼ����ENCRYPT_MODE��ʾ����ģʽ
		c.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] src = str.getBytes();
		// ���ܣ���������cipherByte
		cipherByte = c.doFinal(src);
		return new Base64Encoder().encode(cipherByte);
	}

	/**
	 * ���ַ�������
	 * 
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String Decryptor(String baseStr) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// ������Կ����Cipher������г�ʼ����DECRYPT_MODE��ʾ����ģʽ
		c.init(Cipher.DECRYPT_MODE, deskey);
		cipherByte = c.doFinal(new Base64Encoder().decode(baseStr));
		return new String(cipherByte);
	}

	/**
	 * @param args
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static void main(String[] args) throws Exception {
	}

}
