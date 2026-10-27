package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor;

/* JADX INFO: loaded from: classes3.dex */
public final class TransducedAccessor_method_Short extends DefaultTransducedAccessor {
    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public boolean hasValue(Object obj) {
        return true;
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor, com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public String print(Object obj) {
        return DatatypeConverterImpl._printShort(((Bean) obj).get_short());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void parse(Object obj, CharSequence charSequence) {
        ((Bean) obj).set_short(DatatypeConverterImpl._parseShort(charSequence));
    }
}
