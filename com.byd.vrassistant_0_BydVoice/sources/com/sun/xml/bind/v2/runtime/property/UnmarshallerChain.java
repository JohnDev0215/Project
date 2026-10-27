package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.v2.runtime.JAXBContextImpl;

/* JADX INFO: loaded from: classes3.dex */
public final class UnmarshallerChain {
    public final JAXBContextImpl context;
    private int offset = 0;

    public UnmarshallerChain(JAXBContextImpl jAXBContextImpl) {
        this.context = jAXBContextImpl;
    }

    public int allocateOffset() {
        int i = this.offset;
        this.offset = i + 1;
        return i;
    }

    public int getScopeSize() {
        return this.offset;
    }
}
