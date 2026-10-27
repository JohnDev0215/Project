package com.amazon.ion.impl.lite;

import com.amazon.ion.SymbolTable;

/* JADX INFO: loaded from: classes3.dex */
interface IonContext {
    IonContainerLite getContextContainer();

    SymbolTable getContextSymbolTable();

    IonSystemLite getSystem();
}
