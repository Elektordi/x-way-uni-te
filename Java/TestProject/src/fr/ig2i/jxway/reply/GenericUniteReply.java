/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.reply;

import fr.ig2i.jxway.requests.GenericUniteRequest;
import fr.ig2i.jxway.requests.GenericXWayRequest;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public abstract class GenericUniteReply extends GenericXWayReply {

    public static GenericUniteReply fromdata(Stack<Integer> recv, GenericXWayRequest xway_request) {
        if(!(xway_request instanceof GenericUniteRequest)) return null;
        GenericUniteRequest request  = (GenericUniteRequest)xway_request;
        int code = recv.remove(0);
        switch (code) {
            
            case 0x30: // Bit read
                return new ReadInternalBitReply(recv, request);
            
            case 0x63: // Undocumented. Found on a P57203
            case 0x66:
                return new InitReply(recv);
            
            case 0xF0:
                return null; // TODO: Handle UNI-TE v2
            case 0xFB:
                return new MirrorReply(recv, request);
            
            case 0xFD:
                return new FailureReply(recv);
            case 0xFE:
                return new SuccessReply(recv);
                
            case 0xFF:
                return new FailureReply(recv); // Undocumented. Network status ?
 
        }
        return null;
    }
}
