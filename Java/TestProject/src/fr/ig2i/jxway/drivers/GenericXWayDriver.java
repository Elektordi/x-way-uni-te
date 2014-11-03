/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.drivers;

import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.reply.GenericXWayReply;
import fr.ig2i.jxway.requests.GenericXWayRequest;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public abstract class GenericXWayDriver {
    
    protected int timeout = 20;
    protected JXWayAddress source;
    
    protected abstract boolean send(Stack<Integer> data);
    protected abstract Stack<Integer> recv();

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public JXWayAddress getSource() {
        return source;
    }

    public void setSource(JXWayAddress source) {
        this.source = source;
    }
    
    public GenericXWayReply transmit(JXWayAddress addr, GenericXWayRequest request) {
        // TODO Remove me
        send(request.forge(source,addr));
        Stack<Integer> buffer = recv();
        if(buffer==null || buffer.empty()) return null;
        GenericXWayReply reply = GenericXWayReply.fromdata(buffer, request);
        return reply;
    }
    
}
