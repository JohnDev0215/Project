package com.amazon.ion;

import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes3.dex */
public interface IonDatagram extends IonSequence {
    @Override // com.amazon.ion.IonSequence, com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    @Override // com.amazon.ion.IonContainer, java.lang.Iterable
    Iterator<IonValue> iterator();

    ListIterator<IonValue> systemIterator();
}
