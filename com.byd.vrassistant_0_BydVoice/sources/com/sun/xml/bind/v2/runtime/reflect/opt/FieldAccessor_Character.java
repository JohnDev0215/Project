package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.v2.runtime.reflect.Accessor;

/* JADX INFO: loaded from: classes3.dex */
public class FieldAccessor_Character extends Accessor {
    public FieldAccessor_Character() {
        super(Character.class);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public Object get(Object obj) {
        return Character.valueOf(((Bean) obj).f_char);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
    public void set(Object obj, Object obj2) {
        ((Bean) obj).f_char = obj2 == null ? (char) 0 : ((Character) obj2).charValue();
    }
}
