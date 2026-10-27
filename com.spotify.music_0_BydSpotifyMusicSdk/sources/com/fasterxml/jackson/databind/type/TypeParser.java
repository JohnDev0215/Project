package com.fasterxml.jackson.databind.type;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class TypeParser implements Serializable {
    private static final long serialVersionUID = 1;
    protected final TypeFactory _factory;

    public TypeParser(TypeFactory typeFactory) {
        this._factory = typeFactory;
    }
}
