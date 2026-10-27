package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class FieldAccessor_Long extends Accessor {
    public FieldAccessor_Long() {
        super(Long.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Long.valueOf(((Bean) obj).f_long);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).f_long = obj2 == null ? 0L : ((Long) obj2).longValue();
    }
}
