package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.Serializable;
import p.ro1;

/* JADX INFO: loaded from: classes.dex */
public class NullsConstantProvider implements ro1, Serializable {
    public static final NullsConstantProvider c = new NullsConstantProvider(null);
    public static final NullsConstantProvider e = new NullsConstantProvider(null);
    private static final long serialVersionUID = 1;
    protected final AccessPattern _access;
    protected final Object _nullValue;

    public NullsConstantProvider(Object obj) {
        this._nullValue = obj;
        this._access = obj == null ? AccessPattern.c : AccessPattern.e;
    }
}
