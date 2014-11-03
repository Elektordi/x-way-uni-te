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
public class WriteInternalBitRequest extends GenericUniteRequest {
    
    private int address;
    private boolean value;

    public WriteInternalBitRequest(int address, boolean value) {
        this.address = address;
        this.value = value;
    }

    @Override
    protected int getRequestCode() {
        return 0x10;
    }

    @Override
    protected void append_unite_data(Stack<Integer> data) {
        data.add(address&0xFF);
        data.add((address>>8)&0xFF);
        if(value)
            data.add(1);
        else
            data.add(0);
    }
    
    
}
