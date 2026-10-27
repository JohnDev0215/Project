package com.amazon.ion;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IonSequence extends IonContainer, List<IonValue> {
    ValueFactory add();

    void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException;

    boolean add(IonValue ionValue) throws ContainedValueException, NullPointerException;

    @Override // com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();
}
