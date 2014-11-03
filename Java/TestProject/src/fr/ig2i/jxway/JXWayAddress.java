/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway;

/**
 *
 * @author Elektordi
 */
public class JXWayAddress {
    
    private int network;
    private int station;
    private int gate;
    private int module;
    private int port;
    private int ref;
    
    private int size;
    
    public JXWayAddress(String address) {
        // TODO: Decode string address
    }

    @Override
    public String toString() {
        if(size == 3) {
            if(gate==0) {
                return String.format("{%d.%d}SYS", network, station);
            } else {
                return String.format("{%d.%d}.%d", network, station, gate);
            }
        } else if(size == 5) {
            return "?"; // TODO: Build level 5 string
        } else if(size == 6) {
            return "?"; // TODO: Build level 6 string
        }
        return "UNKNOW";
    }
    

    public JXWayAddress(int network, int station) {
        this.network = network;
        this.station = station;
        this.gate = 0;
        this.size = 3;
    }

    public JXWayAddress(int network, int station, int gate) {
        this.network = network;
        this.station = station;
        this.gate = gate;
        this.size = 3;
    }

    public JXWayAddress(int network, int station, int gate, int module, int port) {
        this.network = network;
        this.station = station;
        this.gate = gate;
        this.module = module;
        this.port = port;
        this.size = 5;
    }
    
    public JXWayAddress(int network, int station, int gate, int module, int port, int ref) {
        this.network = network;
        this.station = station;
        this.gate = gate;
        this.module = module;
        this.port = port;
        this.ref = ref;
        this.size = 6;
    }

    public int getGate() {
        return gate;
    }

    public int getModule() {
        return module;
    }

    public int getNetwork() {
        return network;
    }

    public int getPort() {
        return port;
    }

    public int getRef() {
        return ref;
    }

    public int getSize() {
        return size;
    }

    public int getStation() {
        return station;
    }
    
    public static JXWayAddress fromBytes(Integer b1, Integer b2) {
        return new JXWayAddress(b2>>4, b1, b2&0xF);
    }

}
