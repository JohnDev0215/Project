package com.sun.xml.bind.v2.model.core;

import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public interface ElementInfo<T, C> extends Element<T, C> {
    T getContentInMemoryType();

    NonElement<T, C> getContentType();

    ElementPropertyInfo<T, C> getProperty();

    @Override // com.sun.xml.bind.v2.model.core.Element
    ElementInfo<T, C> getSubstitutionHead();

    Collection<? extends ElementInfo<T, C>> getSubstitutionMembers();

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    T getType();
}
