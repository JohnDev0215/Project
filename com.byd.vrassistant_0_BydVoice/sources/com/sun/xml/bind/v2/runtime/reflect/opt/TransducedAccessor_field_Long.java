package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor;

/* JADX INFO: loaded from: classes3.dex */
public final class TransducedAccessor_field_Long extends DefaultTransducedAccessor {
    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public boolean hasValue(Object obj) {
        return true;
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor, com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public String print(Object obj) {
        return DatatypeConverterImpl._printLong(((Bean) obj).f_long);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void parse(Object obj, CharSequence charSequence) {
        ((Bean) obj).f_long = DatatypeConverterImpl._parseLong(charSequence);
    }
}
