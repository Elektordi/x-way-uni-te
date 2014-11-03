/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.requests;

import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class ReadInternalBitRequest extends GenericUniteRequest {
    
    private int address;

    public ReadInternalBitRequest(int address) {
        this.address = address;
    }

    @Override
    protected int getRequestCode() {
        return 0x00;
    }

    @Override
    protected void append_unite_data(Stack<Integer> data) {
        data.add(address&0xFF);
        data.add((address>>8)&0xFF);
    }

    public int getAddress() {
        return address;
    }
    
    

}
