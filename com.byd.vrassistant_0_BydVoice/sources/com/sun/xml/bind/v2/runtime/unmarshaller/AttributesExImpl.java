package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.util.AttributesImpl;

/* JADX INFO: loaded from: classes3.dex */
public final class AttributesExImpl extends AttributesImpl implements AttributesEx {
    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.AttributesEx
    public CharSequence getData(int i) {
        return getValue(i);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.AttributesEx
    public CharSequence getData(String str, String str2) {
        return getValue(str, str2);
    }
}
