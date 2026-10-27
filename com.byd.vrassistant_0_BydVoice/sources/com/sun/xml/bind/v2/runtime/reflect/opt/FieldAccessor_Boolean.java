package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class FieldAccessor_Boolean extends Accessor {
    public FieldAccessor_Boolean() {
        super(Boolean.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Boolean.valueOf(((Bean) obj).f_boolean);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).f_boolean = obj2 == null ? false : ((Boolean) obj2).booleanValue();
    }
}
