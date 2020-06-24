package org.hyperskill.models;


import java.util.List;

public class Miner
{
    private String networkID;
    private List<Block> localChain;
    public NetworkSocketP2P networkSocketP2P;

    public Miner(String network)
    {
        this.networkID = String.format("p2p.%s.blockchain.network", network);
        this.networkSocketP2P = new NetworkSocketP2P(this.networkID, this);
        this.localChain = this.networkSocketP2P.requestNetworkChain(networkID);
    }

    public List<Block> getLocalChain()
    {
        return this.localChain;
    }
}
