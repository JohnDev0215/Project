package com.sun.xml.bind.v2.model.core;

import java.util.List;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface ElementPropertyInfo<T, C> extends PropertyInfo<T, C> {
    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Adapter<T, C> getAdapter();

    List<? extends TypeRef<T, C>> getTypes();

    QName getXmlName();

    boolean isCollectionNillable();

    boolean isCollectionRequired();

    boolean isRequired();

    boolean isValueList();
}
