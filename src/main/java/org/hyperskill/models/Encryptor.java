package org.hyperskill.models;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
    private static SecretKeySpec getSecretKey(String encryptionKey)
                                                throws UnsupportedEncodingException,
                                                NoSuchAlgorithmException
    {

        byte[] key;
        MessageDigest sha = null;
        key = encryptionKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, "AES");
    }

    public static String encrypto(String data, String encryptionKey)
                                    throws UnsupportedEncodingException,
                                    NoSuchAlgorithmException,
                                    NoSuchPaddingException,
                                    InvalidKeyException,
                                    BadPaddingException,
                                    IllegalBlockSizeException
    {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(encryptionKey));
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
    }

    public static String decrypt(String data, String encryptionKey)
                                    throws UnsupportedEncodingException,
                                    NoSuchAlgorithmException,
                                    NoSuchPaddingException,
                                    InvalidKeyException,
                                    BadPaddingException,
                                    IllegalBlockSizeException
    {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(encryptionKey));
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    }
}
