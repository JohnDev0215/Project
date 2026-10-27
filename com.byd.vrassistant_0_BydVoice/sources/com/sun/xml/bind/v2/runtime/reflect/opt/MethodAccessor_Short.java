package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class MethodAccessor_Short extends Accessor {
    public MethodAccessor_Short() {
        super(Short.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Short.valueOf(((Bean) obj).get_short());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).set_short(obj2 == null ? (short) 0 : ((Short) obj2).shortValue());
    }
}
