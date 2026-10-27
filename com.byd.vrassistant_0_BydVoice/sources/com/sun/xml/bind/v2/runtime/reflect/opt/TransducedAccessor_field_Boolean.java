package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor;

/* JADX INFO: loaded from: classes3.dex */
public final class TransducedAccessor_field_Boolean extends DefaultTransducedAccessor {
    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public boolean hasValue(Object obj) {
        return true;
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.DefaultTransducedAccessor, com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public String print(Object obj) {
        return DatatypeConverterImpl._printBoolean(((Bean) obj).f_boolean);
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void parse(Object obj, CharSequence charSequence) {
        Boolean bool_parseBoolean = DatatypeConverterImpl._parseBoolean(charSequence);
        if (bool_parseBoolean != null) {
            ((Bean) obj).f_boolean = bool_parseBoolean.booleanValue();
        }
    }
}
