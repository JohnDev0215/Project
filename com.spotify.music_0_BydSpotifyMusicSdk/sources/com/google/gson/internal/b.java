package com.google.gson.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import p.sa1;
import p.ta1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AbstractSet {
    public final /* synthetic */ LinkedTreeMap c;

    public b(LinkedTreeMap linkedTreeMap) {
        this.c = linkedTreeMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        ta1 ta1VarA;
        Object obj2;
        Object value;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        LinkedTreeMap linkedTreeMap = this.c;
        linkedTreeMap.getClass();
        Object key = entry.getKey();
        ta1 ta1Var = null;
        if (key != null) {
            try {
                ta1VarA = linkedTreeMap.a(key, false);
            } catch (ClassCastException unused) {
                ta1VarA = null;
            }
        } else {
            ta1VarA = null;
        }
        if (ta1VarA != null && ((obj2 = ta1VarA.o) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
            ta1Var = ta1VarA;
        }
        return ta1Var != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new sa1(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        ta1 ta1VarA;
        Object obj2;
        Object value;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        LinkedTreeMap linkedTreeMap = this.c;
        linkedTreeMap.getClass();
        Object key = entry.getKey();
        ta1 ta1Var = null;
        if (key != null) {
            try {
                ta1VarA = linkedTreeMap.a(key, false);
            } catch (ClassCastException unused) {
                ta1VarA = null;
            }
        } else {
            ta1VarA = null;
        }
        if (ta1VarA != null && ((obj2 = ta1VarA.o) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
            ta1Var = ta1VarA;
        }
        if (ta1Var == null) {
            return false;
        }
        linkedTreeMap.c(ta1Var, true);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c.size;
    }
}
