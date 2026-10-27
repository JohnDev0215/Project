package com.amazon.ion.impl;

import com.amazon.ion.IonContainer;
import com.amazon.ion.IonValue;

/* JADX INFO: loaded from: classes3.dex */
public interface _Private_IonContainer extends IonContainer {
    @Override // com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    IonValue get_child(int i);

    int get_child_count();
}
