package org.hyperskill.models;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class Block {
    private String hash;
    private String prevHash;
    private String data;

    public Block(String data, String prevHash, String encryptionKey)
            throws NoSuchPaddingException,
            UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException
    {

        this.data = Encryptor.encrypto(data, encryptionKey);
        this.prevHash = (prevHash != null)? prevHash: Hash.empty();
        this.hash = Hash.calculateHash(data);
    }

    public String getData(String encryptionKey)
            throws NoSuchPaddingException,
            UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException
    {

        return Encryptor.decrypt(this.data, encryptionKey);
    }

    public String getHash()
    {
        return this.hash;
    }

    public String getPrevHash()
    {
        return this.prevHash;
    }
}
