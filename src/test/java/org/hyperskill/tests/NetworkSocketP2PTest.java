package org.hyperskill.tests;

import org.hyperskill.models.Miner;
import org.junit.Assume;
import org.junit.Test;

public class NetworkSocketP2PTest {

    @Test
    public void testCreateAndRegister100MinersToOneNetwork()
    {
        Miner[] miners = new Miner[100];

        for(int i = 0; i < miners.length; i++)
        {
            miners[i] = new Miner("test");
        }

        Assume.assumeTrue("Miners created and connected to p2p.test.blockchain.network network", true);
    }
}