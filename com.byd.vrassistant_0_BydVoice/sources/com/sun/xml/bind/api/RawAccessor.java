package com.sun.xml.bind.api;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RawAccessor<B, V> {
    public abstract V get(B b) throws AccessorException;

    public abstract void set(B b, V v) throws AccessorException;
}
