package com.sun.xml.bind;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class AnyTypeAdapter extends XmlAdapter<Object, Object> {
    @Override // javax.xml.bind.annotation.adapters.XmlAdapter
    public Object marshal(Object obj) {
        return obj;
    }

    @Override // javax.xml.bind.annotation.adapters.XmlAdapter
    public Object unmarshal(Object obj) {
        return obj;
    }
}
