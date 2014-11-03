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
public class InitReply extends GenericUniteReply {

    private int status;

    public InitReply(Stack<Integer> payload) {
        if(payload.size()!=1) {
            status = -1;
            return;
        }
        status = payload.pop();
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        switch (status) {
            case -1:
                return "Bad payload.";
            case 0x00:
                return "OK";
            case 0x18:
                return "Configuration failure.";
        }
        return "Unknow status.";
    }
}
