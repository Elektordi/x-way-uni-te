/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.drivers;

import fr.ig2i.jxway.JXWayAddress;
import fr.ig2i.jxway.JXWayUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elektordi
 */
public class XipDriver extends GenericXWayDriver {

    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private static Stack<Integer> modbus_init;
    private static Stack<Integer> modbus_answer;

    public XipDriver(InetAddress addr, JXWayAddress local) throws IOException {
        socket = new Socket(addr, 502, null, 1621); // Modbus: TCP/502

        out = socket.getOutputStream();
        in = socket.getInputStream();

        // TODO: Make it more smart...
        modbus_init = new Stack<Integer>();
        modbus_init.add(0x00); // init modbus...
        modbus_init.add(0x00);
        modbus_init.add(0x00);
        modbus_init.add(0x01);
        modbus_init.add(0x00);
        //modbus_init.add(11);
        //modbus_init.add(0x00); // ...init modbus
        
        // TODO: Make it more smart...
        modbus_answer = new Stack<Integer>();
        modbus_answer.add(0x00); // init modbus...
        modbus_answer.add(0x00);
        modbus_answer.add(0x00);
        modbus_answer.add(0x01);
        modbus_answer.add(0x00);
        //modbus_answer.add(0x08);
        //modbus_answer.add(0x00); // ...init modbus

        this.source = local;
    }

    public XipDriver(String addr, JXWayAddress local) throws IOException {
        this(InetAddress.getByName(addr), local);
    }
    
    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    @Override
    protected boolean send(Stack<Integer> data) {
        Stack<Integer> tosend = (Stack<Integer>) modbus_init.clone();
        tosend.add(data.size()+1);
        tosend.add(0);
        tosend.addAll(data);
        try {
            JXWayUtils.stackDebug("OUT", tosend);
            JXWayUtils.stackWrite(out, tosend);
        } catch (IOException ex) {
            Logger.getLogger(XipDriver.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    protected Stack<Integer> recv() {
        try {
            Stack<Integer> stack = JXWayUtils.stackRead(in, timeout);
            JXWayUtils.stackDebug(" IN", stack);
            int initsize = modbus_answer.size();
            if(!stack.subList(0, initsize).equals(modbus_answer)) return null;
            Stack<Integer> data = new Stack<Integer>();
            data.addAll(stack.subList(initsize+2, stack.size()));
            return data;
        } catch (IOException ex) {
            Logger.getLogger(XipDriver.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
