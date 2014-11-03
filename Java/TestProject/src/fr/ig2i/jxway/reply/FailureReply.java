/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.reply;

import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class FailureReply extends GenericUniteReply {

    private Stack<Integer> payload;

    public FailureReply(Stack<Integer> payload) {
        this.payload = payload;
    }
    
    public Stack<Integer> getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Failure!";
    }
}
