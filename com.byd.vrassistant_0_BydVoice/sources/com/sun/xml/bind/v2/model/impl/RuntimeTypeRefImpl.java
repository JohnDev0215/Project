package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.Transducer;
import java.lang.reflect.Type;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
final class RuntimeTypeRefImpl extends TypeRefImpl<Type, Class> implements RuntimeTypeRef {
    public RuntimeTypeRefImpl(RuntimeElementPropertyInfoImpl runtimeElementPropertyInfoImpl, QName qName, Type type, boolean z, String str) {
        super(runtimeElementPropertyInfoImpl, qName, type, z, str);
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeRefImpl, com.sun.xml.bind.v2.model.core.NonElementRef
    public RuntimeNonElement getTarget() {
        return (RuntimeNonElement) super.getTarget();
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeNonElementRef
    public Transducer getTransducer() {
        return RuntimeModelBuilder.createTransducer(this);
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeRefImpl, com.sun.xml.bind.v2.model.core.NonElementRef
    public RuntimePropertyInfo getSource() {
        return (RuntimePropertyInfo) this.owner;
    }
}
