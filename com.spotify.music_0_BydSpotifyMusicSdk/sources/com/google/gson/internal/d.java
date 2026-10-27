package com.google.gson.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p.ta1;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Iterator {
    public ta1 c;
    public ta1 e = null;
    public int k;
    public final /* synthetic */ LinkedTreeMap l;

    public d(LinkedTreeMap linkedTreeMap) {
        this.l = linkedTreeMap;
        this.c = linkedTreeMap.header.l;
        this.k = linkedTreeMap.modCount;
    }

    public final ta1 a() {
        ta1 ta1Var = this.c;
        LinkedTreeMap linkedTreeMap = this.l;
        if (ta1Var == linkedTreeMap.header) {
            throw new NoSuchElementException();
        }
        if (linkedTreeMap.modCount != this.k) {
            throw new ConcurrentModificationException();
        }
        this.c = ta1Var.l;
        this.e = ta1Var;
        return ta1Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c != this.l.header;
    }

    @Override // java.util.Iterator
    public final void remove() {
        ta1 ta1Var = this.e;
        if (ta1Var == null) {
            throw new IllegalStateException();
        }
        LinkedTreeMap linkedTreeMap = this.l;
        linkedTreeMap.c(ta1Var, true);
        this.e = null;
        this.k = linkedTreeMap.modCount;
    }
}
