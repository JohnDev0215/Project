package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.databind.JsonMappingException;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
public abstract class MismatchedInputException extends JsonMappingException {
    protected Class<?> _targetType;

    public MismatchedInputException(q71 q71Var, Class cls, String str) {
        super(q71Var, str);
        this._targetType = cls;
    }
}
