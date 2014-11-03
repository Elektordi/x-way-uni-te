/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.reply;

import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.requests.GenericXWayRequest;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public abstract class GenericXWayReply {

    protected int type = 0;
    protected int service = 0;
    protected boolean refused = false;
    protected JXWayAddress from;
    protected JXWayAddress to;
    protected GenericXWayRequest xway_request;

    public static GenericXWayReply fromdata(Stack<Integer> recv, GenericXWayRequest request) {
        int npdu = recv.remove(0);
        int type = npdu >> 4;
        int service = (npdu >> 2) & 3;
        boolean refused = (npdu & 2) > 0;
        boolean extension = (npdu & 1) > 0;
        JXWayAddress from = JXWayAddress.fromBytes(recv.remove(0), recv.remove(0));
        JXWayAddress to = JXWayAddress.fromBytes(recv.remove(0), recv.remove(0));
        // TODO: Implement levels 5 and 6
        if (type != 15) {
            return null;
        }
        if (service == 0) {
            GenericUniteReply unite = GenericUniteReply.fromdata(recv, request);
            if(unite==null) return null;
            unite.type = type;
            unite.refused = refused;
            unite.from = from;
            unite.to = to;
            unite.xway_request = request;
            return unite;
        }

        return null; // TODO: Implement telegram
    }

    public JXWayAddress getFrom() {
        return from;
    }

    public boolean isRefused() {
        return refused;
    }

    public int getService() {
        return service;
    }

    public JXWayAddress getTo() {
        return to;
    }

    public int getType() {
        return type;
    }
}
