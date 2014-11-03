/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.reply;

import fr.ig2i.jxway.requests.GenericUniteRequest;
import fr.ig2i.jxway.requests.ReadInternalBitRequest;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class ReadInternalBitReply extends GenericUniteReply {

    private boolean value = false;
    private boolean force = false;
    private boolean ok = false;
    private ReadInternalBitRequest request;

    public ReadInternalBitReply(Stack<Integer> payload, GenericUniteRequest unite_request) {
        if (!(unite_request instanceof ReadInternalBitRequest)) {
            return;
        }
        request = (ReadInternalBitRequest) unite_request;

        if (payload.size() != 2) {
            return;
        }

        int m = (1 << (request.getAddress() % 8));

        int f = payload.pop();
        int v = payload.pop();

        force = (f & m) > 0;
        value = (v & m) > 0;

        ok = true;
    }

    public boolean isForced() {
        return force;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (!ok) {
            return "KO";
        }
        if (force) {
            if (value) {
                return "F1";
            } else {
                return "F0";
            }
        }
        if (value) {
            return "1";
        } else {
            return "0";
        }
    }
}
