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
public class InitRequest extends GenericUniteRequest {

    @Override
    protected int getRequestCode() {
        return 0x33;
    }

    @Override
    protected void append_unite_data(Stack<Integer> data) {
        data.add(0x01); // Init type
    }
}
