package com.sun.xml.bind.v2.model.core;

import java.util.Collection;
import java.util.Set;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface ReferencePropertyInfo<T, C> extends PropertyInfo<T, C> {
    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Adapter<T, C> getAdapter();

    C getDOMHandler();

    Set<? extends Element<T, C>> getElements();

    WildcardMode getWildcard();

    QName getXmlName();

    boolean isCollectionNillable();

    boolean isCollectionRequired();

    boolean isMixed();

    boolean isRequired();

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Collection<? extends TypeInfo<T, C>> ref();
}
