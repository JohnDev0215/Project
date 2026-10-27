package com.sun.xml.bind.v2.model.core;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeRef<T, C> extends NonElementRef<T, C> {
    String getDefaultValue();

    QName getTagName();

    boolean isNillable();
}
