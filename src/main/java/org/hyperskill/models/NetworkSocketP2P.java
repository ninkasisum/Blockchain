package org.hyperskill.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkSocketP2P {
    private static final List<String> avaliableNetworks = new ArrayList<String>();
    private static final Map<String, List<Miner>> nodesPerNetwork = new HashMap<String, List<Miner>>();
    private Miner registeredMiner;
    public NetworkSocketP2P(String networkID, Miner minerToConnect)
    {
        if (networkID != null && minerToConnect != null)
        {

            if (!avaliableNetworks.contains(networkID))
            {
                avaliableNetworks.add(networkID);
                nodesPerNetwork.put(networkID, new ArrayList<Miner>());
            }

            List<Miner> networkNodes = nodesPerNetwork.get(networkID);
            networkNodes.add(minerToConnect);
            nodesPerNetwork.put(networkID, networkNodes);

            this.registeredMiner = minerToConnect;

        } else throw new RuntimeException("There was an error while miner register");
    }

    public List<Block> requestNetworkChain(String networkID)
    {
        if (avaliableNetworks.contains(networkID))
        {
            List<Miner> networkNodes = nodesPerNetwork.get(networkID);
            if (networkNodes != null && networkNodes.size() > 0)
            {
                List<Block> chainToTest = networkNodes.get(0).networkSocketP2P.requestLocalChain();
                if (networkNodes.size() == 1)  return chainToTest;
                else
                {
                    int agreementsCounter = 0;
                    for(Miner node: networkNodes)
                    {
                        if (node.networkSocketP2P.compareBlockChain(chainToTest))
                        {
                            agreementsCounter++;
                        }
                    }
                    if (agreementsCounter > networkNodes.size()/2)
                        return chainToTest;
                    else
                    {
                        nodesPerNetwork.get(networkID).remove(0); // disconect
                        return this.requestNetworkChain(networkID);
                    }
                }
            } return new ArrayList<Block>();
        } throw new RuntimeException(String.format("There was an Error trying to access network: %s"));
    }

    public List<Block> requestLocalChain()  {  return this.registeredMiner.getLocalChain();  }

    public Boolean compareBlockChain(List<Block> chainToTest)
    {
        Boolean equals = true;
        List<Block> chainToCompare = this.registeredMiner.getLocalChain();
        if (chainToCompare == chainToTest)
            return true;

        if (chainToCompare != null && chainToTest != null
            && chainToCompare.size() == chainToTest.size())
        {
            for(int i = 0; i < chainToCompare.size(); i++)
            {
                Block blockToCompare = chainToCompare.get(i);
                Block blockToTest = chainToTest.get(i);

                if (blockToCompare.getHash() != blockToTest.getHash()
                || blockToCompare.getPrevHash() != blockToTest.getPrevHash())
                    return false;
            }
        } else return false;
        return true;
    }
}
