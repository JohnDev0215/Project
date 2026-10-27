package com.sun.xml.bind.v2.model.core;

/* JADX INFO: loaded from: classes3.dex */
public interface EnumLeafInfo<T, C> extends LeafInfo<T, C> {
    NonElement<T, C> getBaseType();

    C getClazz();

    Iterable<? extends EnumConstant> getConstants();
}
