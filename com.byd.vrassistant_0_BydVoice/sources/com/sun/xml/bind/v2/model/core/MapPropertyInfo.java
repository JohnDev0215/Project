package com.sun.xml.bind.v2.model.core;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface MapPropertyInfo<T, C> extends PropertyInfo<T, C> {
    NonElement<T, C> getKeyType();

    NonElement<T, C> getValueType();

    QName getXmlName();

    boolean isCollectionNillable();
}
