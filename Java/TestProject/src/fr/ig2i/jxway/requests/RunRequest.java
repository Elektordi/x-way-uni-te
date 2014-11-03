/*
 * This source file is part of the "J-X-Way UNI-TE" project
 * Copyright (C) 2011, Guillaume 'Elektordi' Genty
 */
package fr.ig2i.jxway.requests;

import java.util.Stack;

/**
 *
 * @author Elektordi
 */
public class RunRequest extends GenericUniteRequest {

    private int instance;
    public static final int ALL = 0;
    public static final int PT_0 = 1;
    public static final int PT_1 = 2;
    public static final int FAST = 3;
    public static final int MAST = 4;

    @Override
    protected int getRequestCode() {
        return 0x24;
    }

    @Override
    protected void append_unite_data(Stack<Integer> data) {
        if (instance > 0) {
            data.add(0x82); // Segment
            data.add(0x01); // Type
            data.add(instance);
        }
    }

    public RunRequest(int instance) {
        this.instance = instance;
    }

    public RunRequest() {
        this.instance = ALL;
    }
}
