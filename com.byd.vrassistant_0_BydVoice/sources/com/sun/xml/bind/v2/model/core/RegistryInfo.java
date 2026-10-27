package com.sun.xml.bind.v2.model.core;

import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public interface RegistryInfo<T, C> {
    C getClazz();

    Set<TypeInfo<T, C>> getReferences();
}
