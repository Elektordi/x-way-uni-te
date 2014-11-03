/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.reply;

import fr.ig2i.jxway.requests.GenericUniteRequest;
import fr.ig2i.jxway.requests.MirrorRequest;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class MirrorReply extends GenericUniteReply {
 
    private Stack<Integer> payload;
    private MirrorRequest request;

    public MirrorReply(Stack<Integer> payload, GenericUniteRequest unite_request) {
        if(!(unite_request instanceof MirrorRequest)) return;
        request = (MirrorRequest)unite_request;
        
        this.payload = payload;
    }
    
    public Stack<Integer> getPayload() {
        return payload;
    }
    
    public boolean isValid() {
        return request.getPayload().equals(payload);
    }

    @Override
    public String toString() {
        if(isValid()) return "OK";
        return "KO";
    }
    
}
