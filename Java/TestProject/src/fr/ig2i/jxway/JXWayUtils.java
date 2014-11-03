/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class JXWayUtils {
    
    public static void stackDebug(String type, Stack<Integer> b) {
        String line = type + " : ";
        for (Integer c : b) {
            if(c<16) line += "0";
            line += Integer.toHexString(c) + " ";
        }
        System.out.println(line);

    }

    public static boolean stackWrite(OutputStream out, Stack<Integer> s) throws IOException {
        byte[] b = new byte[s.size()];
        for(int i=0;i<s.size();i++) {
            b[i] = s.get(i).byteValue();
        }
        out.write(b);
        return true; // TODO: Check write
    }

    public static Stack<Integer> stackRead(InputStream in, int timeout) throws IOException {
        while(in.available()==0) Thread.yield(); // TODO: Handle timeout
        Stack<Integer> v = new Stack<Integer>();
        while(in.available()>0) {
            v.add(in.read());
        }
        return v;
    }
    
    public static int randomByteAsInt() {
        return (int)(Math.random()*0xFF);
    }
}
