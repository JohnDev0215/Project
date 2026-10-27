package com.google.common.collect;

import defpackage.n3;
import defpackage.xi3;

/* JADX INFO: compiled from: RegularImmutableSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<E> extends d<E> {
    public static final Object[] l;
    public static final f<Object> m;
    public final transient Object[] g;
    public final transient int h;
    public final transient Object[] i;
    public final transient int j;
    public final transient int k;

    static {
        Object[] objArr = new Object[0];
        l = objArr;
        m = new f<>(0, 0, 0, objArr, objArr);
    }

    public f(int i, int i2, int i3, Object[] objArr, Object[] objArr2) {
        this.g = objArr;
        this.h = i;
        this.i = objArr2;
        this.j = i2;
        this.k = i3;
    }

    @Override // com.google.common.collect.a
    public final int a(int i, Object[] objArr) {
        Object[] objArr2 = this.g;
        int i2 = this.k;
        System.arraycopy(objArr2, 0, objArr, i, i2);
        return i + i2;
    }

    @Override // com.google.common.collect.a
    public final Object[] b() {
        return this.g;
    }

    @Override // com.google.common.collect.a
    public final int c() {
        return this.k;
    }

    @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.i;
            if (objArr.length != 0) {
                int iP1 = n3.p1(obj);
                while (true) {
                    int i = iP1 & this.j;
                    Object obj2 = objArr[i];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    iP1 = i + 1;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.a
    public final int d() {
        return 0;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: f */
    public final xi3<E> iterator() {
        return p().listIterator(0);
    }

    @Override // com.google.common.collect.d, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.k;
    }

    @Override // com.google.common.collect.d
    public final b<E> u() {
        return b.p(this.k, this.g);
    }

    @Override // com.google.common.collect.d
    public final boolean w() {
        return true;
    }
}
