package com.sun.xml.bind.v2.model.core;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface Element<T, C> extends TypeInfo<T, C> {
    QName getElementName();

    ClassInfo<T, C> getScope();

    Element<T, C> getSubstitutionHead();
}
