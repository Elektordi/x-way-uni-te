/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.requests;

import fr.ig2i.jxway.JXWayUtils;
import fr.ig2i.jxway.reply.MirrorReply;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class MirrorRequest extends GenericUniteRequest {
    
    private Stack<Integer> payload;

    public MirrorRequest(Stack<Integer> payload) {
        this.payload = payload;
    }

    public MirrorRequest(int payload_lenght) {
        payload = new Stack<Integer>();
        payload.ensureCapacity(payload_lenght);
        for(int i=0; i<payload_lenght; i++) {
            payload.add(JXWayUtils.randomByteAsInt());
        }
    }

    public MirrorRequest() {
        payload = new Stack<Integer>();
        payload.add(JXWayUtils.randomByteAsInt());
    }

    public Stack<Integer> getPayload() {
        return payload;
    }

    public void setPayload(Stack<Integer> payload) {
        this.payload = payload;
    }

    @Deprecated
    public boolean checkReply(MirrorReply reply) {
        if(reply==null) return false;
        if(payload==null) return false;
        if(reply.getPayload()==null) return false;
        return reply.getPayload().equals(payload);
    }

    @Override
    protected int getRequestCode() {
        return 0xFA;
    }

    @Override
    protected void append_unite_data(Stack<Integer> data) {
        data.addAll(payload);
    }
    
    
}
