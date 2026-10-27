package com.sun.xml.bind.v2.model.core;

import com.sun.xml.bind.v2.WellKnownNamespace;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface NonElement<T, C> extends TypeInfo<T, C> {
    public static final QName ANYTYPE_NAME = new QName(WellKnownNamespace.XML_SCHEMA, "anyType");

    QName getTypeName();

    boolean isSimpleType();
}
