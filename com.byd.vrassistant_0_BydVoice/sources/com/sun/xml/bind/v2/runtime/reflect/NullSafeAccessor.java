package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;

/* JADX INFO: loaded from: classes3.dex */
public class NullSafeAccessor<B, V, P> extends Accessor<B, V> {
    private final Accessor<B, V> core;
    private final Lister<B, V, ?, P> lister;

    public NullSafeAccessor(Accessor<B, V> accessor, Lister<B, V, ?, P> lister) {
        super(accessor.getValueType());
        this.core = accessor;
        this.lister = lister;
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public V get(B b) throws AccessorException {
        V v = this.core.get(b);
        if (v != null) {
            return v;
        }
        this.lister.endPacking(this.lister.startPacking(b, this.core), b, this.core);
        return this.core.get(b);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(B b, V v) throws AccessorException {
        this.core.set(b, v);
    }
}
