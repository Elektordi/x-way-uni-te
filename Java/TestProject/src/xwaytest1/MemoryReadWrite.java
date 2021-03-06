/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package xwaytest1;

import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.drivers.XipDriver;
import fr.ig2i.jxway.reply.GenericXWayReply;
import fr.ig2i.jxway.reply.ReadInternalBitReply;
import fr.ig2i.jxway.reply.SuccessReply;
import fr.ig2i.jxway.requests.ReadInternalBitRequest;
import fr.ig2i.jxway.requests.WriteInternalBitRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elektordi
 */
public class MemoryReadWrite {

    public static void main(String[] args) {
        try {

            XipDriver xip = new XipDriver("192.168.209.251", new JXWayAddress(8, 40));
            JXWayAddress addr = new JXWayAddress(9, 1);
            GenericXWayReply reply;

            System.out.println("Reading %M1 = TRUE in " + addr + ":");
            reply = xip.transmit(addr, new ReadInternalBitRequest(1));
            if (reply instanceof ReadInternalBitReply) {
                ReadInternalBitReply r = (ReadInternalBitReply) reply;
                System.out.println("OK="+r.isOk()+" Value=" + r.getValue() + " Force=" + r.isForced());
            } else {
                System.out.println("KO");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MemoryReadWrite.class.getName()).log(Level.SEVERE, null, ex);
            }


            System.out.println("Setting %M1 = TRUE in " + addr + ":");
            reply = xip.transmit(addr, new WriteInternalBitRequest(1, true));
            if (reply instanceof SuccessReply) {
                System.out.println("OK");
            } else {
                System.out.println("KO");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MemoryReadWrite.class.getName()).log(Level.SEVERE, null, ex);
            }


            System.out.println("Reading %M1 = TRUE in " + addr + ":");
            reply = xip.transmit(addr, new ReadInternalBitRequest(1));
            if (reply instanceof ReadInternalBitReply) {
                ReadInternalBitReply r = (ReadInternalBitReply) reply;
                System.out.println("OK="+r.isOk()+" Value=" + r.getValue() + " Force=" + r.isForced());
            } else {
                System.out.println("KO");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MemoryReadWrite.class.getName()).log(Level.SEVERE, null, ex);
            }



            System.out.println("Setting %M1 = FALSE in " + addr + ":");
            reply = xip.transmit(addr, new WriteInternalBitRequest(1, false));
            if (reply instanceof SuccessReply) {
                System.out.println("OK");
            } else {
                System.out.println("KO");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MemoryReadWrite.class.getName()).log(Level.SEVERE, null, ex);
            }


            System.out.println("Reading %M1 = TRUE in " + addr + ":");
            reply = xip.transmit(addr, new ReadInternalBitRequest(1));
            if (reply instanceof ReadInternalBitReply) {
                ReadInternalBitReply r = (ReadInternalBitReply) reply;
                System.out.println("OK="+r.isOk()+" Value=" + r.getValue() + " Force=" + r.isForced());
            } else {
                System.out.println("KO");
            }


        } catch (IOException ex) {
            Logger.getLogger(MemoryReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
