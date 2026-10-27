package com.amazon.ion;

import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public interface IonContainer extends IonValue, Iterable<IonValue> {
    @Override // com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    @Override // java.lang.Iterable
    Iterator<IonValue> iterator();

    boolean remove(IonValue ionValue);

    int size();
}
