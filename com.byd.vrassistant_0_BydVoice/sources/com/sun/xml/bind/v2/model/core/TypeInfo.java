package com.sun.xml.bind.v2.model.core;

import com.sun.xml.bind.v2.model.annotation.Locatable;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeInfo<T, C> extends Locatable {
    boolean canBeReferencedByIDREF();

    T getType();
}
