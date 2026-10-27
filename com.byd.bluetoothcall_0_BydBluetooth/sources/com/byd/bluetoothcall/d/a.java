package com.byd.bluetoothcall.d;

import d.a.e;
import d.a.i;

/* JADX INFO: compiled from: InitialValueObservable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends e<T> {
    @Override // d.a.e
    protected final void o(i<? super T> iVar) {
        s(iVar);
        iVar.onNext(r());
    }

    protected abstract T r();

    protected abstract void s(i<? super T> iVar);
}
