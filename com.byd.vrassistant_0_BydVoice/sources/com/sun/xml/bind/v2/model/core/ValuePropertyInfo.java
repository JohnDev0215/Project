package com.sun.xml.bind.v2.model.core;

/* JADX INFO: loaded from: classes3.dex */
public interface ValuePropertyInfo<T, C> extends PropertyInfo<T, C>, NonElementRef<T, C> {
    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Adapter<T, C> getAdapter();
}
