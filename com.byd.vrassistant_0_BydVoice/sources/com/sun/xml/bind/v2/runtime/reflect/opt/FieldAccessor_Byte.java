package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class FieldAccessor_Byte extends Accessor {
    public FieldAccessor_Byte() {
        super(Byte.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Byte.valueOf(((Bean) obj).f_byte);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).f_byte = obj2 == null ? (byte) 0 : ((Byte) obj2).byteValue();
    }
}
