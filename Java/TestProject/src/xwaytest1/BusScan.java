/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package xwaytest1;

import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.drivers.XipDriver;
import fr.ig2i.jxway.reply.GenericXWayReply;
import fr.ig2i.jxway.reply.MirrorReply;
import fr.ig2i.jxway.requests.MirrorRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elektordi
 */
public class BusScan {

    public static void main(String[] args) {
        try {

            XipDriver xip = new XipDriver("192.168.209.251", new JXWayAddress(8, 20));
            MirrorRequest mirror = new MirrorRequest();
            GenericXWayReply reply;
            JXWayAddress addr;

            for (int i = 0; i < 4; i++) {
                addr = new JXWayAddress(9, i);
                reply = xip.transmit(addr, mirror);
                if (reply instanceof MirrorReply && ((MirrorReply)reply).isValid()) {
                    System.out.println("Got answer from 9."+i+" !");
                } else {
                    System.out.println("No reply from 9."+i+"...");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(BusScan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
