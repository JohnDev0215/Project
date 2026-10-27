package com.fasterxml.jackson.databind.exc;

import p.q71;

/* JADX INFO: loaded from: classes.dex */
public class InvalidFormatException extends MismatchedInputException {
    private static final long serialVersionUID = 1;
    protected final Object _value;

    public InvalidFormatException(q71 q71Var, String str, Object obj, Class cls) {
        super(q71Var, cls, str);
        this._value = obj;
    }
}
