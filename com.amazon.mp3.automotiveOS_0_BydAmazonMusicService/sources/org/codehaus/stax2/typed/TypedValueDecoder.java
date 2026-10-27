package org.codehaus.stax2.typed;

/* JADX INFO: loaded from: classes7.dex */
public abstract class TypedValueDecoder {
    public abstract void decode(String str) throws IllegalArgumentException;

    public abstract void handleEmptyValue() throws IllegalArgumentException;
}
