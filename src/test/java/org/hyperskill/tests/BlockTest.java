package org.hyperskill.tests;

import junit.framework.TestCase;
import org.hyperskill.models.Block;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class BlockTest {
    @Test
    public void testeBlockCreationAndDataRetrieve()
            throws NoSuchPaddingException,
            UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {

        Block[] chain = new Block[100];

        String prevHash = null;
        for(int i = 0; i < chain.length; i++)
        {
            String data = String.format("Hello, I'am block %d", i);
            String encryptionKey = String.format("encryptionKey%d", i);
            Block block = new Block(data, prevHash, encryptionKey);
            prevHash = block.getHash();
            chain[i] = block;
        }

        for(int i = 0; i < chain.length; i++)
        {
            String expectedData = String.format("Hello, I'am block %d", i);
            String encryptionKey = String.format("encryptionKey%d", i);
            Block block = chain[i];
            String retrievedData = block.getData(encryptionKey);

            if (!expectedData.equals(retrievedData))
                throw new AssertionError("There was an error while getting data from block");
        }

        Assume.assumeTrue("Data retrieved with success", true);
    }
}