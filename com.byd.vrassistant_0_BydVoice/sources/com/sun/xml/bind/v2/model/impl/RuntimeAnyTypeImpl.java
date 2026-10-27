package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.runtime.Transducer;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
final class RuntimeAnyTypeImpl extends AnyTypeImpl<Type, Class> implements RuntimeNonElement {
    static final RuntimeNonElement theInstance = new RuntimeAnyTypeImpl();

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeNonElement
    public <V> Transducer<V> getTransducer() {
        return null;
    }

    private RuntimeAnyTypeImpl() {
        super(Utils.REFLECTION_NAVIGATOR);
    }
}
