/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.requests;

import fr.ig2i.jxway.JXWayAddress;
import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public abstract class GenericXWayRequest {

    // TODO: Check values in setters
    protected int type = 15;
    protected int service = 0;    
    protected boolean refused = false;
    
    protected abstract void append_xway_data(Stack<Integer> data);

    public Stack<Integer> forge(JXWayAddress from, JXWayAddress to) {
        Stack<Integer> data = new Stack<Integer>();
        int npdu = (type<<4) + (service<<1);
        if(refused) npdu += 2;
        //if(from.getSize()==5 || to.getSize()==5) npdu += 1;
        data.add(npdu);
        data.add(from.getStation());
        data.add( (from.getNetwork()<<4) + (from.getGate()) );
        data.add(to.getStation());
        data.add( (to.getNetwork()<<4) + (to.getGate()) );
        // TODO: Implement levels 5 and 6
        append_xway_data(data);
        return data;
    }
    
}
