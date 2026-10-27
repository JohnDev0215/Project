package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.lk;
import defpackage.q61;
import defpackage.sk3;
import defpackage.yi3;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: LazyStringArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends d<String> implements q61, RandomAccess {
    public static final g0 g;
    public final ArrayList f;

    static {
        g0 g0Var = new g0();
        g0Var.e = false;
        g = g0Var;
    }

    public g0() {
        this(10);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        a();
        this.f.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.d, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        a();
        this.f.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        String str;
        ArrayList arrayList = this.f;
        Object obj = arrayList.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof lk) {
            lk lkVar = (lk) obj;
            str = lkVar.z();
            if (lkVar.q()) {
                arrayList.set(i, str);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            str = new String(bArr, c0.a);
            if (sk3.a.c(0, 0, bArr.length, bArr) == 0) {
                arrayList.set(i, str);
            }
        }
        return str;
    }

    @Override // com.google.protobuf.c0.e
    public final c0.e h(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.f);
        return new g0((ArrayList<Object>) arrayList);
    }

    @Override // defpackage.q61
    public final List<?> k() {
        return Collections.unmodifiableList(this.f);
    }

    @Override // defpackage.q61
    public final void l(lk lkVar) {
        a();
        this.f.add(lkVar);
        ((AbstractList) this).modCount++;
    }

    @Override // defpackage.q61
    public final q61 m() {
        return this.e ? new yi3(this) : this;
    }

    @Override // defpackage.q61
    public final Object n(int i) {
        return this.f.get(i);
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        Object objRemove = this.f.remove(i);
        ((AbstractList) this).modCount++;
        return objRemove instanceof String ? (String) objRemove : objRemove instanceof lk ? ((lk) objRemove).z() : new String((byte[]) objRemove, c0.a);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        a();
        Object obj2 = this.f.set(i, (String) obj);
        return obj2 instanceof String ? (String) obj2 : obj2 instanceof lk ? ((lk) obj2).z() : new String((byte[]) obj2, c0.a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f.size();
    }

    public g0(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        a();
        if (collection instanceof q61) {
            collection = ((q61) collection).k();
        }
        boolean zAddAll = this.f.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    public g0(q61 q61Var) {
        this.f = new ArrayList(q61Var.size());
        addAll(q61Var);
    }

    public g0(ArrayList<Object> arrayList) {
        this.f = arrayList;
    }
}
