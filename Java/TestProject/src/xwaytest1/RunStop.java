/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package xwaytest1;

import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.drivers.XipDriver;
import fr.ig2i.jxway.reply.GenericXWayReply;
import fr.ig2i.jxway.reply.InitReply;
import fr.ig2i.jxway.reply.SuccessReply;
import fr.ig2i.jxway.requests.InitRequest;
import fr.ig2i.jxway.requests.RunRequest;
import fr.ig2i.jxway.requests.StopRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elektordi
 */
public class RunStop {

    public static void main(String[] args) {
        try {

            XipDriver xip = new XipDriver("192.168.209.251", new JXWayAddress(8, 20));
            JXWayAddress addr = new JXWayAddress(9, 1);
            GenericXWayReply reply;

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunStop.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Sending INIT to 9.3:");
            reply = xip.transmit(addr, new InitRequest());
            System.out.println(reply.toString());


            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunStop.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Sending RUN to " + addr + ":");
            reply = xip.transmit(addr, new RunRequest());
            System.out.println(reply.toString());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunStop.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Sending STOP to " + addr + ":");
            reply = xip.transmit(addr, new StopRequest());
            System.out.println(reply.toString());

        } catch (IOException ex) {
            Logger.getLogger(RunStop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
