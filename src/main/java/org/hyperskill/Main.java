package org.hyperskill;

import org.hyperskill.models.Block;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args)
            throws NoSuchPaddingException,
            UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {

        Block[] chain = new Block[3];

        String prevHash = null;
        for(int i = 0; i < chain.length; i++)
        {
            String data = String.format("Hello, I'am block %d", i);
            String encryptionKey = String.format("encryptionKey%d", i);
            Block block = new Block(data, prevHash, encryptionKey);
            prevHash = block.getHash();
            chain[i] = block;
        }

        System.out.println("BlockChain Created!");
    }
}
