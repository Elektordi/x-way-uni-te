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
public abstract class GenericUniteRequest extends GenericXWayRequest {

    protected abstract int getRequestCode();
    //protected abstract int getCategoryCode();
    protected abstract void append_unite_data(Stack<Integer> data);
    
    @Override
    protected void append_xway_data(Stack<Integer> data) {
        // TODO: Implement UNI-TE v2
        data.add(getRequestCode());
        data.add(7); // TODO: Implement category
        append_unite_data(data);
    }
}
