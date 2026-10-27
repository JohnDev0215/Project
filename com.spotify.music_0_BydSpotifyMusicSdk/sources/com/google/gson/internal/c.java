package com.google.gson.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import p.sa1;
import p.ta1;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractSet {
    public final /* synthetic */ LinkedTreeMap c;

    public c(LinkedTreeMap linkedTreeMap) {
        this.c = linkedTreeMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.c.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new sa1(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        LinkedTreeMap linkedTreeMap = this.c;
        linkedTreeMap.getClass();
        ta1 ta1VarA = null;
        if (obj != null) {
            try {
                ta1VarA = linkedTreeMap.a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (ta1VarA != null) {
            linkedTreeMap.c(ta1VarA, true);
        }
        return ta1VarA != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c.size;
    }
}
