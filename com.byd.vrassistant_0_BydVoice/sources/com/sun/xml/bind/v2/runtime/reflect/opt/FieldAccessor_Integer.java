package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class FieldAccessor_Integer extends Accessor {
    public FieldAccessor_Integer() {
        super(Integer.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Integer.valueOf(((Bean) obj).f_int);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).f_int = obj2 == null ? 0 : ((Integer) obj2).intValue();
    }
}
