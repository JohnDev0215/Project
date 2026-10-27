package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class MethodAccessor_Integer extends Accessor {
    public MethodAccessor_Integer() {
        super(Integer.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Integer.valueOf(((Bean) obj).get_int());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).set_int(obj2 == null ? 0 : ((Integer) obj2).intValue());
    }
}
