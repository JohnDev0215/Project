package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class MethodAccessor_Boolean extends Accessor {
    public MethodAccessor_Boolean() {
        super(Boolean.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Boolean.valueOf(((Bean) obj).get_boolean());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).set_boolean(obj2 == null ? false : ((Boolean) obj2).booleanValue());
    }
}
