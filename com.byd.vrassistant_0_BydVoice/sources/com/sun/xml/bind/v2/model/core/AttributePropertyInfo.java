package com.sun.xml.bind.v2.model.core;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface AttributePropertyInfo<T, C> extends PropertyInfo<T, C>, NonElementRef<T, C> {
    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Adapter<T, C> getAdapter();

    @Override // com.sun.xml.bind.v2.model.core.NonElementRef
    NonElement<T, C> getTarget();

    QName getXmlName();

    boolean isRequired();
}
